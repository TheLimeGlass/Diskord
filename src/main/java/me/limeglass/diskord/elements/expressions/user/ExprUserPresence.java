package me.limeglass.diskord.elements.expressions.user;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.IPresence;
import sx.blah.discord.handle.obj.IUser;

@Name("User - Presence")
@Description("Gets the presence(s) from the user(s). The presence is like the status and playing text of the user.")
@Properties({"discordusers", "presence[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] user[s]")
public class ExprUserPresence extends DiskordPropertyExpression<IUser, IPresence> {

	@Override
	protected IPresence[] get(Event event, IUser[] users) {
		if (isNull(event)) return null;
		for (IUser user : users) {
			collection.add(user.getPresence());
		}
		return collection.toArray(new IPresence[collection.size()]);
	}
}
