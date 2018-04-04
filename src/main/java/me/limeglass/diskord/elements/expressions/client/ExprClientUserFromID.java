package me.limeglass.diskord.elements.expressions.client;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.DetermineSingle;
import me.limeglass.diskord.utils.annotations.Patterns;
import me.limeglass.diskord.utils.annotations.RegisterType;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IUser;

@Name("Client - User from ID")
@Description("Grab the users from the defined ID(s).")
@Patterns("discord user[s] (seen by|from) [[the] (client|bot)[s]] %discordclients% (with|of) [the] [user] [id[s]] %strings/numbers%")
@RegisterType("discorduser")
@DetermineSingle
public class ExprClientUserFromID extends DiskordExpression<IUser> {
	
	@Override
	protected IUser[] get(Event event) {
		if (expressions.get(0) == null || expressions.get(1) == null) return null;
		Set<IUser> users = new HashSet<IUser>();
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			for (Object id : expressions.get(1).getAll(event)) {
				if (id instanceof String) {
					Long number = Long.parseLong((String)id);
					users.add(client.getUserByID(number));
				} else if (id instanceof Number) {
					Long number = ((Number)id).longValue();
					users.add(client.getUserByID(number));
				}
			}
		}
		return users.toArray(new IUser[users.size()]);
	}
}