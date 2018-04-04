package me.limeglass.diskord.elements.expressions.client;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import me.limeglass.diskord.utils.annotations.RegisterType;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IMessage;

@Name("Client - Messages")
@Description("Gets a list of all messages visible to the client(s) on every shard.")
@Properties({"discordclients", "messages[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] (client|bot)[s]")
@RegisterType("discordmessage")
public class ExprClientMessages extends DiskordPropertyExpression<IDiscordClient, IMessage> {

	@Override
	protected IMessage[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		Set<IMessage> messages = new HashSet<IMessage>();
		for (IDiscordClient client : clients) {
			messages.addAll(client.getMessages());
		}
		return messages.toArray(new IMessage[messages.size()]);
	}
}