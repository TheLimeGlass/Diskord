package me.limeglass.diskord.elements.expressions;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.Patterns;
import me.limeglass.diskord.utils.annotations.RegisterType;
import me.limeglass.diskord.utils.annotations.Single;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

@Name("New bot")
@Description("Returns a new Client (Bot) with the given token.")
@Patterns("[a] [new] discord (client|bot) (from|with) token %string%")
@RegisterType("discordclient")
@Single
public class ExprNewBot extends DiskordExpression<IDiscordClient> {
	
	@Override
	protected IDiscordClient[] get(Event event) {
		if (areNull(event)) return null;
		IDiscordClient client = new ClientBuilder().withToken(expressions.getSingle(event, String.class)).login();
		if (client == null || !client.isLoggedIn()) return null;
		return new IDiscordClient[] {client};
	}
}