package me.limeglass.diskord.elements.expressions.user;

import java.util.HashSet;
import java.util.Set;

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
		Set<Permissions> permissions = new HashSet<Permissions>();
		for (IUser user : expressions.getAll(event, IUser.class)) {
			for (IGuild guild : expressions.getAll(event, IGuild.class)) {
				permissions.addAll(user.getPermissionsForGuild(guild));
			}
		}
		return permissions.toArray(new Permissions[permissions.size()]);
	}
}