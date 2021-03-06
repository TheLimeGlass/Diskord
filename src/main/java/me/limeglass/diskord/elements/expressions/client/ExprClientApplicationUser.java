package me.limeglass.diskord.elements.expressions.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IUser;

@Name("Client - Application user")
@Description("Gets the associated application's user of the client(s).")
@Properties({"discordclients", "application (owner|user)[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[[the] (client|bot)[s]]")
public class ExprClientApplicationUser extends DiskordPropertyExpression<IDiscordClient, IUser> {

	@Override
	protected IUser[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		for (IDiscordClient client : clients) {
			collection.add(client.getApplicationOwner());
		}
		return collection.toArray(new IUser[collection.size()]);
	}
}
