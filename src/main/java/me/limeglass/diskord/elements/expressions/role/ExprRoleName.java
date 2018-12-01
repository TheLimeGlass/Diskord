package me.limeglass.diskord.elements.expressions.role;

import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Changers;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.IRole;

@Name("Role - Name")
@Description("Gets the name of the defined role(s).")
@Properties({"discordroles", "name[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] role[s]")
@Changers(ChangeMode.SET)
public class ExprRoleName extends DiskordPropertyExpression<IRole, String> {

	@Override
	protected String[] get(Event event, IRole[] roles) {
		if (isNull(event)) return null;
		for (IRole role : roles) {
			collection.add(role.getName());
		}
		return collection.toArray(new String[collection.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (IRole role : expressions.getAll(event, IRole.class)) {
			role.changeName((String)delta[0]);
		}
	}
}
