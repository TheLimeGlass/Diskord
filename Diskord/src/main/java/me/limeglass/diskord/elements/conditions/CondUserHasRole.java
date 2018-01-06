package me.limeglass.diskord.elements.conditions;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordCondition;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

@Name("Client - Has role")
@Description("Check if a user has a role.")
@Patterns("[user] %discorduser% (1¦(does [have]|has)|2¦(does|has)(n't| not) [have]) role %discordrole%")
public class CondUserHasRole extends DiskordCondition {

	public boolean check(Event event) {
		if (areNull(event)) return false;
		return (expressions.getSingle(event, IUser.class).hasRole(expressions.getSingle(event, IRole.class))) ? isNegated() : !isNegated();
	}
}