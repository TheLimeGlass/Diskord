package me.limeglass.diskord.elements.expressions.client;

import java.util.HashSet;
import java.util.Set;

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
		Set<String> descriptions = new HashSet<String>();
		for (IDiscordClient client : clients) {
			descriptions.add(client.getApplicationDescription());
		}
		return descriptions.toArray(new String[descriptions.size()]);
	}
}