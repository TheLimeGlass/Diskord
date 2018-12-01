package me.limeglass.diskord.elements.expressions.user;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.DetermineSingle;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

@Name("User - Display name")
@Description("Gets the display name(s) of the user(s) in the defined guild(s).")
@Patterns("display name[s] (from|of) %discordusers% in [[the] (guild|server)] %discordguilds%")
@DetermineSingle
public class ExprUserDisplayName extends DiskordExpression<String> {
	
	@Override
	protected String[] get(Event event) {
		if (areNull(event)) return null;
		IGuild[] guilds = expressions.getAll(event, IGuild.class);
		for (IUser user : expressions.getAll(event, IUser.class)) {
			for (IGuild guild : guilds) {
				collection.add(user.getDisplayName(guild));
			}
		}
		return collection.toArray(new String[collection.size()]);
	}
}
