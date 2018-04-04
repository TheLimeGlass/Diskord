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

@Name("Role - Mention")
@Description("Gets the mention of the defined role(s).")
@Properties({"discordroles", "mention[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[[the] role[s]]")
public class ExprRoleMention extends DiskordPropertyExpression<IRole, String> {

	@Override
	protected String[] get(Event event, IRole[] roles) {
		if (isNull(event)) return null;
		Set<String> mentions = new HashSet<String>();
		for (IRole role : roles) {
			mentions.add(role.mention());
		}
		return mentions.toArray(new String[mentions.size()]);
	}
}