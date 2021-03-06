package me.limeglass.diskord.elements.expressions.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.DetermineSingle;
import me.limeglass.diskord.utils.annotations.Patterns;
import me.limeglass.diskord.utils.annotations.RegisterType;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IPrivateChannel;
import sx.blah.discord.handle.obj.IUser;

@Name("Client - Private channel")
@Description("Grab all private channels from the user(s) for the client(s). If the private message channel doesn't exist, the bot will create one.")
@Patterns("private channel from [[the] (client|bot)[s]] %discordclients% (with|of|for) user[s] %discordusers%")
@DetermineSingle
@RegisterType("discordprivatechannel")
public class ExprClientPrivateChannel extends DiskordExpression<IPrivateChannel> {
	
	@Override
	protected IPrivateChannel[] get(Event event) {
		if (areNull(event)) return null;
		IUser[] users = expressions.getAll(event, IUser.class);
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			for (IUser user : users) {
				collection.add(client.getOrCreatePMChannel(user));
			}
		}
		return collection.toArray(new IPrivateChannel[collection.size()]);
	}
}
