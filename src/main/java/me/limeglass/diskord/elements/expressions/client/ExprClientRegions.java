package me.limeglass.diskord.elements.expressions.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import me.limeglass.diskord.utils.annotations.RegisterType;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IRegion;

@Name("Client - Regions")
@Description("Gets a list of all discord regions. Needs the client instances to grab them.")
@Properties({"discordclients", "region[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] (client|bot)[s]")
@RegisterType("discordregion")
public class ExprClientRegions extends DiskordPropertyExpression<IDiscordClient, IRegion> {

	@Override
	protected IRegion[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		for (IDiscordClient client : clients) {
			collection.addAll(client.getRegions());
		}
		return collection.toArray(new IRegion[collection.size()]);
	}
}
