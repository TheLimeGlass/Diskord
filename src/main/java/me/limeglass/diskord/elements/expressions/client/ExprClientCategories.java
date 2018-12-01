package me.limeglass.diskord.elements.expressions.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import me.limeglass.diskord.utils.annotations.RegisterType;
import me.limeglass.diskord.utils.annotations.User;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.ICategory;

@Name("Client - Categories")
@Description("Gets a list of all categories visible to the client(s) on every shard.")
@Properties({"discordclients", "categories[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] (client|bot)[s]")
@RegisterType("discordcategory")
@User("discordcategor(y|ies)")
public class ExprClientCategories extends DiskordPropertyExpression<IDiscordClient, ICategory> {

	@Override
	protected ICategory[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		for (IDiscordClient client : clients) {
			collection.addAll(client.getCategories());
		}
		return collection.toArray(new ICategory[collection.size()]);
	}
}
