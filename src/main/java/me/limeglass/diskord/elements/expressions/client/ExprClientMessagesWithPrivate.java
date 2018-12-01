package me.limeglass.diskord.elements.expressions.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IMessage;

@Name("Client - Messages with private")
@Description("Gets a list of all messages and private channels that are visible to the client(s) on every shard.")
@Properties({"discordclients", "messages[s] with private [channels]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] (client|bot)[s]")
public class ExprClientMessagesWithPrivate extends DiskordPropertyExpression<IDiscordClient, IMessage> {

	@Override
	protected IMessage[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		for (IDiscordClient client : clients) {
			collection.addAll(client.getMessages(true));
		}
		return collection.toArray(new IMessage[collection.size()]);
	}
}
