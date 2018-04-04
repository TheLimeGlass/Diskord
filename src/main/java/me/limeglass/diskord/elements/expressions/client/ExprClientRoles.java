package me.limeglass.diskord.elements.expressions.client;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import me.limeglass.diskord.utils.annotations.RegisterType;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IRole;

@Name("Client - Roles")
@Description("Gets a list of all discord roles. Needs the client instances to grab them.")
@Properties({"discordclients", "roles[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] (client|bot)[s]")
@RegisterType("discordrole")
public class ExprClientRoles extends DiskordPropertyExpression<IDiscordClient, IRole> {

	@Override
	protected IRole[] get(Event event, IDiscordClient[] clients) {
		if (isNull(event)) return null;
		Set<IRole> roles = new HashSet<IRole>();
		for (IDiscordClient client : clients) {
			roles.addAll(client.getRoles());
		}
		return roles.toArray(new IRole[roles.size()]);
	}
}