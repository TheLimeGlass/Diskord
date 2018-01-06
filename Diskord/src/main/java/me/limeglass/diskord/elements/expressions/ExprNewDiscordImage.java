package me.limeglass.diskord.elements.expressions;

import java.io.File;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.Patterns;
import me.limeglass.diskord.utils.annotations.RegisterType;
import me.limeglass.diskord.utils.annotations.Single;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.Image;

@Name("New discord image")
@Description("Returns a new discord image that is used for avatars.")
@Patterns("[a] [new] discord image from [(file|user)] %string/discorduser%")
@RegisterType("discordimage")
@Single
public class ExprNewDiscordImage extends DiskordExpression<Image> {
	
	@Override
	protected Image[] get(Event event) {
		if (expressions.get(0) == null) return null;
		Object expression = expressions.get(0).getSingle(event);
		if (expression instanceof String) {
			return new Image[] {Image.forFile(new File((String) expression))};
		} else if (expression instanceof IUser) {
			return new Image[] {Image.forUser((IUser)expression)};
		}
		return new Image[] {Image.defaultAvatar()};
	}
}