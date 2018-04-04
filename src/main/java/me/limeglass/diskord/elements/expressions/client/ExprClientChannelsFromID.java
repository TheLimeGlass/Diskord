package me.limeglass.diskord.elements.expressions.client;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.DetermineSingle;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;

@Name("Client - Channels from ID")
@Description("Grab all channels that the client(s) can see by ID.")
@Patterns("[discord] channels (seen by|from) [[the] (client|bot)[s]] %discordclients% (with|of) [the] id[s] %strings/numbers%")
@DetermineSingle
public class ExprClientChannelsFromID extends DiskordExpression<IChannel> {
	
	@Override
	protected IChannel[] get(Event event) {
		if (expressions.get(0) == null || expressions.get(1) == null) return null;
		Set<IChannel> channels = new HashSet<IChannel>();
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			for (Object id : expressions.get(1).getAll(event)) {
				if (id instanceof String) {
					Long number = Long.parseLong((String)id);
					channels.add(client.getChannelByID(number));
				} else if (id instanceof Number) {
					Long number = ((Number)id).longValue();
					channels.add(client.getChannelByID(number));
				}
			}
		}
		return channels.toArray(new IChannel[channels.size()]);
	}
}