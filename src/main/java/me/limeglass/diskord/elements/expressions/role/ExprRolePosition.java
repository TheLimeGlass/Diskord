package me.limeglass.diskord.elements.expressions.role;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.IRole;

@Name("Role - Position")
@Description("Gets the position number of the defined role(s).")
@Properties({"discordroles", "position[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] role[s]")
public class ExprRolePosition extends DiskordPropertyExpression<IRole, Number> {

	@Override
	protected Number[] get(Event event, IRole[] roles) {
		if (isNull(event)) return null;
		for (IRole role : roles) {
			collection.add(role.getPosition());
		}
		return collection.toArray(new Number[collection.size()]);
	}
}
