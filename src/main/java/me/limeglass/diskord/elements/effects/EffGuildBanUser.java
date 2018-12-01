package me.limeglass.diskord.elements.effects;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

@Name("Guild - Ban user")
@Description("Ban the user(s) from the defined guild(s).")
@Patterns("ban[ish] [the] [discord] user[s] %discordusers% (from|in) [[the] (guild|server)[s]] %discordguilds% [(by reason of|because [of]|on account of|due to) %-string%] [[with] delete[d] messages [from] [past] %-number% days]")
public class EffGuildBanUser extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (isNull(event, IUser.class, IGuild.class)) return;
		String reason = expressions.getSingle(event, String.class);
		IUser[] users = expressions.getAll(event, IUser.class);
		boolean delete = isNull(event, Number.class);
		boolean message = isNull(event, String.class);
		int days = expressions.getInt(event);
		for (IGuild guild : expressions.getAll(event, IGuild.class)) {
			user: for (IUser user : users) {
				if (!message) {
					if (!delete) {
						guild.banUser(user, reason, days);
						continue user;
					}
					guild.banUser(user, reason);
					continue user;
				} else if (!delete) {
					guild.banUser(user, days);
					continue user;
				}
				guild.banUser(user);
			}
		}
	}
}
