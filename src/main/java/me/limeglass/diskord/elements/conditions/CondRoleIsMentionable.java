package me.limeglass.diskord.elements.conditions;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordCondition;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IRole;

@Name("Role - Is Mentionable")
@Description("Check if a role is mentionable.")
@Patterns("[role] %discordrole% (1¦(can be mention(ed|able)|is mentionable)|2¦(can('t| not) be mention(ed|able)|is(n't| not) mentionable))")
public class CondRoleIsMentionable extends DiskordCondition {

	public boolean check(Event event) {
		if (areNull(event)) return false;
		return (expressions.getSingle(event, IRole.class).isMentionable()) ? isNegated() : !isNegated();
	}
}