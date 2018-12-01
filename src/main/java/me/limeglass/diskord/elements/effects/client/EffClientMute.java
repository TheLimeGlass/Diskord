package me.limeglass.diskord.elements.effects.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.Utils;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;

@Name("Client - Muted")
@Description("Change the muted state of the client(s) in the defined guild(s).")
@Patterns("(set|change) [the] mute[d] [state] of [[the] (client|bot)[s]] %discordclients% in [[the] (guild|server)] %numbers/strings/discordguilds% to %boolean%")
public class EffClientMute extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		Object[] inputs = expressions.get(1).getAll(event);
		boolean state = expressions.getSingle(event, Boolean.class);
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			for (Object object : inputs) {
				IGuild guild = Utils.getGuild(client, object);
				if (guild == null)
					continue;
				client.mute(guild, state);
			}
		}
	}
}
