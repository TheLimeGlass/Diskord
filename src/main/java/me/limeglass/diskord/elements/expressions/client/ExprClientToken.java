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

@Name("Client - Token")
@Description("Gets a list of all token(s) of the client(s).")
@Properties({"discordclients", "token[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] (client|bot)[s]")
@RegisterType("discordtoken")
public class ExprClientToken extends DiskordPropertyExpression<IDiscordClient, String> {

	@Override
	protected String[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		Set<String> tokens = new HashSet<String>();
		for (IDiscordClient client : clients) {
			tokens.add(client.getToken());
		}
		return tokens.toArray(new String[tokens.size()]);
	}
}