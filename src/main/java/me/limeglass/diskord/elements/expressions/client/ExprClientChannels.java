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
import sx.blah.discord.handle.obj.IChannel;

@Name("Client - Channels")
@Description("Gets a list of all channels visible to the client(s) on every shard.")
@Properties({"discordclients", "channels[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] (client|bot)[s]")
@RegisterType("discordchannel")
public class ExprClientChannels extends DiskordPropertyExpression<IDiscordClient, IChannel> {

	@Override
	protected IChannel[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		Set<IChannel> channels = new HashSet<IChannel>();
		for (IDiscordClient client : clients) {
			channels.addAll(client.getChannels());
		}
		return channels.toArray(new IChannel[channels.size()]);
	}
}