package me.limeglass.diskord.elements.effects;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IRole;

@Name("Role - Delete")
@Description("Deletes the defined roles(s).")
@Patterns("(remove|delete) [the] [discord] role[s] %discordroles%")
public class EffRoleDelete extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		for (IRole role : expressions.getAll(event, IRole.class)) {
			role.delete();
		}
	}
}