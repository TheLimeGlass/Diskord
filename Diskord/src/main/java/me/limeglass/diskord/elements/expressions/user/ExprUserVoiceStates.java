package me.limeglass.diskord.elements.expressions.user;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceState;

@Name("User - Voice states")
@Description("Gets the voice state(s) from the user(s). This will grab the voice state of the user on all guilds, the other syntax like this is for defined guilds.")
@Properties({"discordusers", "voice state[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] user[s]")
public class ExprUserVoiceStates extends DiskordPropertyExpression<IUser, IVoiceState> {

	@Override
	protected IVoiceState[] get(Event event, IUser[] users) {
		if (isNull(event)) return null;
		Set<IVoiceState> voiceStates = new HashSet<IVoiceState>();
		for (IUser user : users) {
			voiceStates.addAll(user.getVoiceStates().values());
		}
		return voiceStates.toArray(new IVoiceState[voiceStates.size()]);
	}
}