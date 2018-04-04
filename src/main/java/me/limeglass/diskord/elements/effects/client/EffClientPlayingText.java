package me.limeglass.diskord.elements.effects.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;

@Name("Client - Change playing text")
@Description("Change the playing text of the client(s).")
@Patterns("(set|change) [the] (game|playing) text of [[the] (client|bot)[s]] %discordclients% to %string%")
public class EffClientPlayingText extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			client.changePlayingText(expressions.getSingle(event, String.class));
		}
	}
}