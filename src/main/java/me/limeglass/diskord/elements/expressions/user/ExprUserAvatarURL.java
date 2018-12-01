package me.limeglass.diskord.elements.expressions.user;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.IUser;

@Name("User - Avatar URL")
@Description("Gets the avatar URL(s) from the user(s).")
@Properties({"discordusers", "avatar[s] URL[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] user[s]")
public class ExprUserAvatarURL extends DiskordPropertyExpression<IUser, String> {

	@Override
	protected String[] get(Event event, IUser[] users) {
		if (isNull(event)) return null;
		for (IUser user : users) {
			collection.add(user.getAvatarURL());
		}
		return collection.toArray(new String[collection.size()]);
	}
}
