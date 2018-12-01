package me.limeglass.diskord.elements.expressions.user;

import org.bukkit.DyeColor;
import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.util.Color;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.DetermineSingle;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

@Name("User - Colour")
@Description("Gets the rank colour(s) from the user(s) in the guild(s). This returns as a Skript colour for easier handling for the user.")
@Patterns("colo[u]r[s] (from|of) %discordusers% in [[the] (guild|server)] %discordguilds%")
@DetermineSingle
public class ExprUserColour extends DiskordExpression<Color> {
	
	@Override
	protected Color[] get(Event event) {
		if (areNull(event)) return null;
		IGuild[] guilds = expressions.getAll(event, IGuild.class);
		for (IUser user : expressions.getAll(event, IUser.class)) {
			for (IGuild guild : guilds) {
				int rgb = user.getColorForGuild(guild).getRGB();
				org.bukkit.Color color = org.bukkit.Color.fromRGB(rgb);
				collection.add(Color.byWoolColor(DyeColor.getByColor(color)));
			}
		}
		return collection.toArray(new Color[collection.size()]);
	}
}
