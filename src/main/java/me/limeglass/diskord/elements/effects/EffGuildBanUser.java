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
		if (isNull(event, IUser.class) || isNull(event, IGuild.class)) return;
		for (IGuild guild : expressions.getAll(event, IGuild.class)) {
			user: for (IUser user : expressions.getAll(event, IUser.class)) {
				if (!isNull(event, String.class)) {
					if (!isNull(event, Number.class)) {
						guild.banUser(user, expressions.getSingle(event, String.class), expressions.getInt(event));
						continue user;
					}
					guild.banUser(user, expressions.getSingle(event, String.class));
					continue user;
				} else if (!isNull(event, Number.class)) {
					guild.banUser(user, expressions.getInt(event));
					continue user;
				}
				guild.banUser(user);
			}
		}
	}
}