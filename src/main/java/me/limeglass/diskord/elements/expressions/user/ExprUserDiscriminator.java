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

@Name("User - Discriminator")
@Description("Gets the discriminator(s) from the user(s).")
@Properties({"discordusers", "discriminator[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] user[s]")
public class ExprUserDiscriminator extends DiskordPropertyExpression<IUser, String> {

	@Override
	protected String[] get(Event event, IUser[] users) {
		if (isNull(event)) return null;
		Set<String> discriminators = new HashSet<String>();
		for (IUser user : users) {
			discriminators.add(user.getDiscriminator());
		}
		return discriminators.toArray(new String[discriminators.size()]);
	}
}