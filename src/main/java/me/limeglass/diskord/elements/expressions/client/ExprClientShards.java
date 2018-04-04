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
import sx.blah.discord.api.IShard;

@Name("Client - Shards")
@Description("Gets a list of all shards of the client(s).")
@Properties({"discordclients", "shards[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] (client|bot)[s]")
@RegisterType("discordshard")
public class ExprClientShards extends DiskordPropertyExpression<IDiscordClient, IShard> {

	@Override
	protected IShard[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		Set<IShard> shards = new HashSet<IShard>();
		for (IDiscordClient client : clients) {
			shards.addAll(client.getShards());
		}
		return shards.toArray(new IShard[shards.size()]);
	}
}