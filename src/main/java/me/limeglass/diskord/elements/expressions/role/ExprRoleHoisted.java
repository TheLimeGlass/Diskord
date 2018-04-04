package me.limeglass.diskord.elements.expressions.role;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Changers;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.IRole;

@Name("Role - Hoisted state")
@Description("Gets the hoisted state of the defined role(s).")
@Properties({"discordroles", "hoist[ed] state[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[[the] role[s]]")
@Changers(ChangeMode.SET)
public class ExprRoleHoisted extends DiskordPropertyExpression<IRole, Boolean> {

	@Override
	protected Boolean[] get(Event event, IRole[] roles) {
		if (isNull(event)) return null;
		Set<Boolean> hoisted = new HashSet<Boolean>();
		for (IRole role : roles) {
			hoisted.add(role.isHoisted());
		}
		return hoisted.toArray(new Boolean[hoisted.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (IRole role : expressions.getAll(event, IRole.class)) {
			role.changeHoist((boolean)delta[0]);
		}
	}
}