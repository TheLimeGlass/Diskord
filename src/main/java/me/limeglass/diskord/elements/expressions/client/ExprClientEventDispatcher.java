package me.limeglass.diskord.elements.expressions.client;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import me.limeglass.diskord.utils.annotations.RegisterType;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

@Name("Client - Event dispatcher")
@Description("Gets the event distpatcher of the client(s).")
@Properties({"discordclients", "[event] distpatcher", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] (client|bot)[s]")
@RegisterType("discordeventdispatcher")
public class ExprClientEventDispatcher extends DiskordPropertyExpression<IDiscordClient, EventDispatcher> {

	@Override
	protected EventDispatcher[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		for (IDiscordClient client : clients) {
			collection.add(client.getDispatcher());
		}
		return collection.toArray(new EventDispatcher[collection.size()]);
	}
}
