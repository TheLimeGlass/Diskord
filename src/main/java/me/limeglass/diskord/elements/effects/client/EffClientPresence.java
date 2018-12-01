package me.limeglass.diskord.elements.effects.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import me.limeglass.diskord.utils.annotations.RegisterEnum;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.handle.obj.StatusType;

@Name("Client - Do not disturb")
@Description("Change the do not disturb state of the client(s). The string is optional, and will change the game/playing text aswell.")
@Patterns("(change|set) presence [state] of [[the] (client|bot)[s]] %discordclients% to %statustype% [with %activitytype% and (string|message) %string%]")
@RegisterEnum(ExprClass = StatusType.class, value = "statustype")
public class EffClientPresence extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (isNull(event, IDiscordClient.class, StatusType.class)) return;
		StatusType status = expressions.getSingle(event, StatusType.class);
		if (!isNull(event, ActivityType.class, String.class)) {
			ActivityType activity = expressions.getSingle(event, ActivityType.class);
			String message = expressions.getSingle(event, String.class);
			for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
				client.changePresence(status, activity, message);
			}
		} else {
			for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
				client.changePresence(status);
			}
		}
	}
}
