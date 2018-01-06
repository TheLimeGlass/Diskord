package me.limeglass.diskord.elements.effects.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.Image;

@Name("Client - Change playing text")
@Description("Change the playing text of the client(s).")
@Patterns("(set|change) [the] [discord] avatar[s] of [[the] (client|bot)[s]] %discordclients% to %discordimage%")
public class EffClientAvatar extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			client.changeAvatar(expressions.getSingle(event, Image.class));
		}
	}
}