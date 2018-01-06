package me.limeglass.diskord.elements.effects.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;

@Name("Client - Streaming")
@Description("Change the streaming state of the client(s). The string will change the game/streamer/playing text.")
@Patterns("enable [the] streaming state of [[the] (client|bot)[s]] %discordclients% (for|with) [(streamer|playing|game) [text]] %string% (with|at) [url] %string%")
public class EffClientStreaming extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			client.streaming(expressions.getSingle(event, String.class, 0), expressions.getSingle(event, String.class, 1));
		}
	}
}