package me.limeglass.diskord.elements.expressions.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.DetermineSingle;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IRegion;

@Name("Client - Regions from ID")
@Description("Grab all discord regions by ID from the client(s).")
@Patterns("[discord] regions (of|from) [[the] (client|bot)[s]] %discordclients% (with|of) [the] id[s] %strings%")
@DetermineSingle
public class ExprClientRegionFromID extends DiskordExpression<IRegion> {
	
	@Override
	protected IRegion[] get(Event event) {
		if (areNull(event)) return null;
		String[] ids = expressions.getAll(event, String.class);
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			for (String id : ids) {
				collection.add(client.getRegionByID(id));
			}
		}
		return collection.toArray(new IRegion[collection.size()]);
	}
}
