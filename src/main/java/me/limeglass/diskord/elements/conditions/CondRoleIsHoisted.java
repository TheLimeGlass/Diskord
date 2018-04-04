package me.limeglass.diskord.elements.conditions;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordCondition;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IRole;

@Name("Role - Is Hoisted")
@Description("Check if a role is hoisted.")
@Patterns("[role] %discordrole% (1¦is|2¦is(n't| not)) hoisted")
public class CondRoleIsHoisted extends DiskordCondition {

	public boolean check(Event event) {
		if (areNull(event)) return false;
		return (expressions.getSingle(event, IRole.class).isHoisted()) ? isNegated() : !isNegated();
	}
}