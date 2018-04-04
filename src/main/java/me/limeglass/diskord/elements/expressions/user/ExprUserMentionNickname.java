package me.limeglass.diskord.elements.expressions.user;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.IUser;

@Name("User - Mention")
@Description("Gets the mention(s) from the user(s) with their nicknames.")
@Properties({"discordusers", "mention[s] with name[ ]name[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] user[s]")
public class ExprUserMentionNickname extends DiskordPropertyExpression<IUser, String> {

	@Override
	protected String[] get(Event event, IUser[] users) {
		if (isNull(event)) return null;
		Set<String> mentions = new HashSet<String>();
		for (IUser user : users) {
			mentions.add(user.mention(true));
		}
		return mentions.toArray(new String[mentions.size()]);
	}
}