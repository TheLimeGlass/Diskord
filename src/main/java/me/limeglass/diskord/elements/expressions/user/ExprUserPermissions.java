package me.limeglass.diskord.elements.expressions.user;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.DetermineSingle;
import me.limeglass.diskord.utils.annotations.Patterns;
import me.limeglass.diskord.utils.annotations.RegisterEnum;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

@Name("User - Permissions")
@Description("Grabs the permissions of the user(s) from a guild. Can have permissions added, removed, deleted etc."
		+ "\nIf you want to modify the permissions of the users, you will have the modify the permissions of the roles from that user.")
@Patterns("permissions[s] (from|of) %discordusers% in [[the] (guild|server)] %discordguilds%")
@DetermineSingle
@RegisterEnum("discordpermission")
public class ExprUserPermissions extends DiskordExpression<Permissions> {
	
	@Override
	protected Permissions[] get(Event event) {
		if (areNull(event)) return null;
		IGuild[] guilds = expressions.getAll(event, IGuild.class);
		for (IUser user : expressions.getAll(event, IUser.class)) {
			for (IGuild guild : guilds) {
				collection.addAll(user.getPermissionsForGuild(guild));
			}
		}
		return collection.toArray(new Permissions[collection.size()]);
	}
}
