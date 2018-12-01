package me.limeglass.diskord.elements.expressions.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;

@Name("Client - Channels with private")
@Description("Gets a list of all channels and private channels that are visible to the client(s) on every shard.")
@Properties({"discordclients", "channels[s] with private [channels]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] (client|bot)[s]")
public class ExprClientChannelsWithPrivate extends DiskordPropertyExpression<IDiscordClient, IChannel> {

	@Override
	protected IChannel[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		for (IDiscordClient client : clients) {
			collection.addAll(client.getChannels(true));
		}
		return collection.toArray(new IChannel[collection.size()]);
	}
}
