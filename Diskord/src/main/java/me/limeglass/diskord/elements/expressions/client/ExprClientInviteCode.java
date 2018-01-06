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
import sx.blah.discord.handle.obj.IInvite;

@Name("Client - Invite")
@Description("Grab the invit from the defined client(s). The string is the unique code of the client(s).")
@Patterns("invit(e|ation) [code] from [[the] (client|bot)] %discordclient% (with|of) [unique code] %string%")
@RegisterType("discordinvite")
@DetermineSingle
public class ExprClientInviteCode extends DiskordExpression<IInvite> {
	
	@Override
	protected IInvite[] get(Event event) {
		if (areNull(event)) return null;
		Set<IInvite> invites = new HashSet<IInvite>();
		for (IDiscordClient client : expressions.getAll(event, IDiscordClient.class)) {
			for (String code : expressions.getAll(event, String.class)) {
				invites.add(client.getInviteForCode(code));
			}
		}
		return invites.toArray(new IInvite[invites.size()]);
	}
}