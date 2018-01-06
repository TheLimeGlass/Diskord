package me.limeglass.diskord.elements.effects;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IVoiceChannel;

@Name("Guild - Afk channel")
@Description("Change the AFK channel of the defined guild(s).")
@Patterns("(set|change) [the] afk [voice] channel (from|in|of) [[the] (guild|server)] %discordguild% to [voice channel] %discordvoicechannel%")
public class EffGuildAfkChannel extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		for (IGuild guild : expressions.getAll(event, IGuild.class)) {
			for (IVoiceChannel channel : expressions.getAll(event, IVoiceChannel.class)) {
				guild.changeAFKChannel(channel);
			}
		}
	}
}