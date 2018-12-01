package me.limeglass.diskord.elements.expressions.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.DetermineSingle;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.ICategory;

@Name("Client - Categories from Name")
@Description("Grab all categories that the client(s) can see by name.")
@Patterns("[discord] categories (seen by|from) [[the] (client|bot)[s]] %discordclients% (with|of) name[s] %strings%")
@DetermineSingle
public class ExprClientCategoriesFromName extends DiskordExpression<ICategory> {
	
	@Override
	protected ICategory[] get(Event event) {
		if (areNull(event)) return null;
		String[] names = expressions.getAll(event, String.class);
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			for (String name : names) {
				collection.addAll(client.getCategoriesByName(name));
			}
		}
		return collection.toArray(new ICategory[collection.size()]);
	}
}
