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

@Name("User - Avatar")
@Description("Gets the avatar(s) hash from the user(s).")
@Properties({"discordusers", "avatar[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] user[s]")
public class ExprUserAvatar extends DiskordPropertyExpression<IUser, String> {

	@Override
	protected String[] get(Event event, IUser[] users) {
		if (isNull(event)) return null;
		Set<String> avatars = new HashSet<String>();
		for (IUser user : users) {
			avatars.add(user.getAvatar());
		}
		return avatars.toArray(new String[avatars.size()]);
	}
}