package me.limeglass.diskord.elements.effects.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;

@Name("Client - Online")
@Description("Change the online state of the client(s). The string is optional, and will change the game/playing text aswell.")
@Patterns("enable [the] online state of [[the] (client|bot)[s]] %discordclients% [with [(playing|game) [text]] %string%]")
public class EffClientOnline extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (isNull(event, IDiscordClient.class)) return;
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			client.online();
			if (!isNull(event, String.class)) {
				client.online(expressions.getSingle(event, String.class));
			}
		}
	}
}