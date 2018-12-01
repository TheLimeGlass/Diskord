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

@Name("Role - Mentionable state")
@Description("Gets the mentionable state of the defined role(s).")
@Properties({"discordroles", "mentionable [state[s]]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[[the] role[s]]")
@Changers(ChangeMode.SET)
public class ExprRoleMentionable extends DiskordPropertyExpression<IRole, Boolean> {

	@Override
	protected Boolean[] get(Event event, IRole[] roles) {
		if (isNull(event)) return null;
		for (IRole role : roles) {
			collection.add(role.isHoisted());
		}
		return collection.toArray(new Boolean[collection.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (IRole role : expressions.getAll(event, IRole.class)) {
			role.changeMentionable((boolean)delta[0]);
		}
	}
}
