package me.limeglass.diskord.elements.effects;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;

@Name("User - Move voice channel")
@Description("Move the user(s) to the defined voice channel(s).")
@Patterns({"(set|change) [the] [discord] voice channel[s] of [[the] user[s]] %discordusers% to [the] [voice channel] %discordvoicechannel%", "move [the] [discord] user[s] %discordusers% to voice channel %discordvoicechannels%"})
public class EffUserVoiceChannelMove extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		for (IUser user : expressions.getAll(event, IUser.class)) {
			user.moveToVoiceChannel(expressions.getSingle(event, IVoiceChannel.class));
		}
	}
}