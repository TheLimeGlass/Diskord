package me.limeglass.diskord.elements.conditions;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordCondition;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;

@Name("Client - Logged in")
@Description("Check if a client is logged in.")
@Patterns("(client|bot) %discordclient% (1¦is|2¦is(n't| not)) logged in")
public class CondClientLoggedIn extends DiskordCondition {

	public boolean check(Event event) {
		if (areNull(event)) return false;
		return (expressions.getSingle(event, IDiscordClient.class).isLoggedIn()) ? isNegated() : !isNegated();
	}
}