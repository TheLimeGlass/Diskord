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
import sx.blah.discord.handle.obj.IGuild;

@Name("Client - Guilds")
@Description("Gets a list of all guilds visible to the client(s) on every shard.")
@Properties({"discordclients", "(guilds|servers)", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] (client|bot)[s]")
@RegisterType("discordguild")
public class ExprClientGuilds extends DiskordPropertyExpression<IDiscordClient, IGuild> {

	@Override
	protected IGuild[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		Set<IGuild> guilds = new HashSet<IGuild>();
		for (IDiscordClient client : clients) {
			guilds.addAll(client.getGuilds());
		}
		return guilds.toArray(new IGuild[guilds.size()]);
	}
}