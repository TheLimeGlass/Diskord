package me.limeglass.diskord.elements.expressions.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.api.IDiscordClient;

@Name("Client - Application description")
@Description("Gets the associated application's description of the client(s).")
@Properties({"discordclients", "application description[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[[the] (client|bot)[s]]")
public class ExprClientApplicationDescription extends DiskordPropertyExpression<IDiscordClient, String> {

	@Override
	protected String[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		for (IDiscordClient client : clients) {
			collection.add(client.getApplicationDescription());
		}
		return collection.toArray(new String[collection.size()]);
	}
}
