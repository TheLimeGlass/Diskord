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
import sx.blah.discord.handle.obj.IVoiceChannel;

@Name("Client - Connected voice channels")
@Description("Gets a list of all voice channels the client(s) are connected too.")
@Properties({"discordclients", "connected voice channel[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] (client|bot)[s]")
@RegisterType("discordvoicechannel")
public class ExprClientVoiceConnectedChannels extends DiskordPropertyExpression<IDiscordClient, IVoiceChannel> {

	@Override
	protected IVoiceChannel[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		Set<IVoiceChannel> channels = new HashSet<IVoiceChannel>();
		for (IDiscordClient client : clients) {
			channels.addAll(client.getConnectedVoiceChannels());
		}
		return channels.toArray(new IVoiceChannel[channels.size()]);
	}
}