package me.limeglass.diskord.elements.expressions.role;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;

@Name("Role - Guild")
@Description("Gets the guild (aka discord server) of the defined role(s).")
@Properties({"discordroles", "(guild|server)[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] role[s]")
public class ExprRoleGuild extends DiskordPropertyExpression<IRole, IGuild> {

	@Override
	protected IGuild[] get(Event event, IRole[] roles) {
		if (isNull(event)) return null;
		for (IRole role : roles) {
			collection.add(role.getGuild());
		}
		return collection.toArray(new IGuild[collection.size()]);
	}
}
