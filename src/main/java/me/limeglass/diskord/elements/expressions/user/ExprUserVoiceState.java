package me.limeglass.diskord.elements.expressions.user;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.DetermineSingle;
import me.limeglass.diskord.utils.annotations.Patterns;
import me.limeglass.diskord.utils.annotations.RegisterType;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceState;

@Name("User - Voice state")
@Description("Gets the voice state(s) of the user(s) in the defined guild(s).")
@Patterns("voice[ ]state[s] (from|of) %discordusers% in [[the] (guild|server)] %discordguilds%")
@DetermineSingle
@RegisterType("discordvoicestate")
public class ExprUserVoiceState extends DiskordExpression<IVoiceState> {
	
	@Override
	protected IVoiceState[] get(Event event) {
		if (areNull(event)) return null;
		IGuild[] guilds = expressions.getAll(event, IGuild.class);
		for (IUser user : expressions.getAll(event, IUser.class)) {
			for (IGuild guild : guilds) {
				collection.add(user.getVoiceStateForGuild(guild));
			}
		}
		return collection.toArray(new IVoiceState[collection.size()]);
	}
}
