package me.limeglass.diskord.elements.conditions;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordCondition;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IRole;

@Name("Role - Is Managed")
@Description("Check if a role is managed by an external service like Twitch.")
@Patterns("[role] %discordrole% (1¦is|2¦is(n't| not)) managed [by (twitch|an external service)]")
public class CondRoleIsManaged extends DiskordCondition {

	public boolean check(Event event) {
		if (areNull(event)) return false;
		return (expressions.getSingle(event, IRole.class).isManaged()) ? isNegated() : !isNegated();
	}
}