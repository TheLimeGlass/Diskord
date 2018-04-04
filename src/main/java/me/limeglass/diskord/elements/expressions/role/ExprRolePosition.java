package me.limeglass.diskord.elements.expressions.role;

import java.util.HashSet;
import java.util.Set;

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
		Set<Number> positions = new HashSet<Number>();
		for (IRole role : roles) {
			positions.add(role.getPosition());
		}
		return positions.toArray(new Number[positions.size()]);
	}
}