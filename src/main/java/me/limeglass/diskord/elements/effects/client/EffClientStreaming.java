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

@Name("Client - Streaming")
@Description("Change the streaming state of the client(s). The string will change the game/streamer/playing text.")
@Patterns("(change|set) streaming presence [state] of [[the] (client|bot)[s]] %discordclients% to %statustype% with (string|message) %string% and url %string%")
@RegisterEnum(ExprClass = ActivityType.class, value = "activitytype")
public class EffClientStreaming extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		StatusType status = expressions.getSingle(event, StatusType.class);
		String message = expressions.getSingle(event, String.class, 0);
		String url = expressions.getSingle(event, String.class, 1);
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			client.changeStreamingPresence(status, message, url);
		}
	}
}
