package me.limeglass.diskord.elements.effects.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.Utils;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;

@Name("Client - Deafen")
@Description("Change the deafen state of the client(s) in the defined guild(s).")
@Patterns("(set|change) [the] deafen [state] of [[the] (client|bot)[s]] %discordclients% in [[the] (guild|server)] %numbers/strings/discordguilds% to %boolean%")
public class EffClientDeafen extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (expressions.get(0) == null || expressions.get(1) == null || expressions.get(2) == null) return;
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			for (Object object : expressions.get(1).getAll(event)) {
				IGuild guild = Utils.getGuild(client, object);
				if (guild != null) continue;
				client.deafen(guild, expressions.getSingle(event, Boolean.class));
			}
		}
	}
}