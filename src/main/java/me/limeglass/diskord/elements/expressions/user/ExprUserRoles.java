package me.limeglass.diskord.elements.expressions.user;

import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.AllChangers;
import me.limeglass.diskord.utils.annotations.DetermineSingle;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

@Name("User - Roles")
@Description("Grabs the roles of the user(s) from a guild. Can have roles added, removed, deleted etc.")
@Patterns("role[s] (from|of) %discordusers% in [[the] (guild|server)] %discordguilds%")
@DetermineSingle
@AllChangers
public class ExprUserRoles extends DiskordExpression<IRole> {
	
	@Override
	protected IRole[] get(Event event) {
		if (areNull(event)) return null;
		IGuild[] guilds = expressions.getAll(event, IGuild.class);
		for (IUser user : expressions.getAll(event, IUser.class)) {
			for (IGuild guild : guilds) {
				collection.addAll(user.getRolesForGuild(guild));
			}
		}
		return collection.toArray(new IRole[collection.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (areNull(event) || delta == null) return;
		switch (mode) {
			case SET:
				change(event, delta, ChangeMode.REMOVE_ALL);
			case ADD:
				for (IUser user : expressions.getAll(event, IUser.class)) {
					for (IGuild guild : expressions.getAll(event, IGuild.class)) {
						for (IRole role : (IRole[])delta) {
							if (!user.getRolesForGuild(guild).contains(role)) user.addRole(role);
						}
					}
				}
				break;
			case REMOVE:
				for (IUser user : expressions.getAll(event, IUser.class)) {
					for (IGuild guild : expressions.getAll(event, IGuild.class)) {
						for (IRole role : (IRole[])delta) {
							if (user.getRolesForGuild(guild).contains(role)) user.removeRole(role);
						}
					}
				}
				break;
			case DELETE:
			case REMOVE_ALL:
			case RESET:
				for (IUser user : expressions.getAll(event, IUser.class)) {
					for (IGuild guild : expressions.getAll(event, IGuild.class)) {
						for (IRole role : user.getRolesForGuild(guild)) {
							user.removeRole(role);
						}
					}
				}
				break;
		}
	}
}
