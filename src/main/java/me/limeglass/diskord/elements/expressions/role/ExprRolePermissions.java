package me.limeglass.diskord.elements.expressions.role;

import java.util.EnumSet;
import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.AllChangers;
import me.limeglass.diskord.utils.annotations.Multiple;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.Permissions;

@Name("Role - Permissions")
@Description("Gets the permissions of the defined role(s).")
@Properties({"discordroles", "permissions", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] role[s]")
@AllChangers
@Multiple
public class ExprRolePermissions extends DiskordPropertyExpression<IRole, Permissions> {

	@Override
	protected Permissions[] get(Event event, IRole[] roles) {
		if (isNull(event)) return null;
		EnumSet<Permissions> permissions = getPermissions(roles);
		return permissions.toArray(new Permissions[permissions.size()]);
	}
	
	@SuppressWarnings("null")
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		EnumSet<Permissions> permissions = null;
		for (Permissions p : (Permissions[])delta) {
			permissions.add(p);
		}
		switch (mode) {
			case SET:
				for (IRole role : expressions.getAll(event, IRole.class)) {
					role.changePermissions(permissions);
				}
				break;
			case ADD:
				for (IRole role : expressions.getAll(event, IRole.class)) {
					EnumSet<Permissions> existing = getPermissions(role);
					existing.addAll(permissions);
					role.changePermissions(existing);
				}
				break;
			case REMOVE_ALL:
			case REMOVE:
				for (IRole role : expressions.getAll(event, IRole.class)) {
					EnumSet<Permissions> existing = getPermissions(role);
					existing.removeAll(permissions);
					role.changePermissions(existing);
				}
				break;
			case DELETE:
			case RESET:
				for (IRole role : expressions.getAll(event, IRole.class)) {
					EnumSet<Permissions> existing = getPermissions(role);
					existing.clear();
					role.changePermissions(existing);
				}
				break;
		}
	}
	
	@SuppressWarnings("null")
	private EnumSet<Permissions> getPermissions(IRole... roles) {
		EnumSet<Permissions> permissions = null;
		for (IRole role : roles) {
			permissions.addAll(role.getPermissions());
		}
		return permissions;
	}
}
