package me.limeglass.diskord.elements.effects.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;

@Name("Client - Invisible")
@Description("Change the invisible state of the client(s).")
@Patterns("enable [the] invisible state of [[the] (client|bot)[s]] %discordclients%")
public class EffClientInvisible extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			client.invisible();
		}
	}
}