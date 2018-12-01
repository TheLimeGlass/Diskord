package me.limeglass.diskord.elements.effects.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;

@Name("Client - Change username")
@Description("Change the username of the client(s).")
@Patterns("(set|change) [the] username of [[the] (client|bot)[s]] %discordclients% to %string%")
public class EffClientUsername extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		String username = expressions.getSingle(event, String.class);
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			client.changeUsername(username);
		}
	}
}
