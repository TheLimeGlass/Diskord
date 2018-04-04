package me.limeglass.diskord.elements.conditions;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordCondition;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IRole;

@Name("Role - Is Deleted")
@Description("Check if a role is deleted.")
@Patterns("[role] %discordrole% (1¦(has been|is)|2¦(has(n't| not) been|is(n't| not))) deleted")
public class CondRoleIsDeleted extends DiskordCondition {

	public boolean check(Event event) {
		if (areNull(event)) return false;
		return (expressions.getSingle(event, IRole.class).isDeleted()) ? isNegated() : !isNegated();
	}
}