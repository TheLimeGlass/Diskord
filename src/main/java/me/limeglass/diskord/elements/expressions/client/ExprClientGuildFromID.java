package me.limeglass.diskord.elements.expressions.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.DetermineSingle;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;

@Name("Client - Guilds from ID")
@Description("Grab all guilds that the client(s) can see by ID.")
@Patterns("[discord] guilds (seen by|from) [[the] (client|bot)[s]] %discordclients% (with|of) [the] id[s] %strings/numbers%")
@DetermineSingle
public class ExprClientGuildFromID extends DiskordExpression<IGuild> {
	
	@Override
	protected IGuild[] get(Event event) {
		if (areNull(event)) return null;
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			for (Object id : expressions.get(1).getAll(event)) {
				if (id instanceof String) {
					Long number = Long.parseLong((String)id);
					collection.add(client.getGuildByID(number));
				} else if (id instanceof Number) {
					Long number = ((Number)id).longValue();
					collection.add(client.getGuildByID(number));
				}
			}
		}
		return collection.toArray(new IGuild[collection.size()]);
	}
}
