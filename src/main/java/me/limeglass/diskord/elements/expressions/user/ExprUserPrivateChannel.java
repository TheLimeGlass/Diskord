package me.limeglass.diskord.elements.expressions.user;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.IPrivateChannel;
import sx.blah.discord.handle.obj.IUser;

@Name("User - Private channel")
@Description("Gets the private message channel(s) of the user(s). If it doesn't exist it will create one.")
@Properties({"discordusers", "private [message] channel[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] user[s]")
public class ExprUserPrivateChannel extends DiskordPropertyExpression<IUser, IPrivateChannel> {

	@Override
	protected IPrivateChannel[] get(Event event, IUser[] users) {
		if (isNull(event)) return null;
		for (IUser user : users) {
			collection.add(user.getOrCreatePMChannel());
		}
		return collection.toArray(new IPrivateChannel[collection.size()]);
	}
}
