package me.limeglass.diskord.elements.expressions.client;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.DetermineSingle;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IUser;

@Name("Client - Users from Name")
@Description("Grab all users that the client(s) can see by name.")
@Patterns("[discord] users (seen by|from) [[the] (client|bot)[s]] %discordclients% (with|of) name[s] %strings%")
@DetermineSingle
public class ExprClientUserFromName extends DiskordExpression<IUser> {
	
	@Override
	protected IUser[] get(Event event) {
		if (areNull(event)) return null;
		Set<IUser> users = new HashSet<IUser>();
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			for (String name : expressions.getAll(event, String.class)) {
				users.addAll(client.getUsersByName(name, true));
			}
		}
		return users.toArray(new IUser[users.size()]);
	}
}