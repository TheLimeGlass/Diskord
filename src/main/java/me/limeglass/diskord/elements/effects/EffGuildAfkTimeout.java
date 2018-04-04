package me.limeglass.diskord.elements.effects;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.util.Timespan;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IGuild;

@Name("Guild - Afk timeout")
@Description("Change the AFK timeout for the AFK channel of the defined guild(s).")
@Patterns("(set|change) [the] afk timeout (from|in|of) [[the] (guild|server)] %discordguild% to %timespan%")
public class EffGuildAfkTimeout extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		for (IGuild guild : expressions.getAll(event, IGuild.class)) {
			guild.changeAFKTimeout((int) expressions.getSingle(event, Timespan.class).getMilliSeconds());
		}
	}
}