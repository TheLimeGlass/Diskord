package me.limeglass.diskord.elements.expressions.role;

import org.bukkit.DyeColor;
import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.util.Color;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Changers;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.IRole;

@Name("Role - Colour")
@Description("Gets the colours of the defined role(s).")
@Properties({"discordroles", "colo[u]r[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] role[s]")
@Changers({ChangeMode.SET, ChangeMode.RESET, ChangeMode.DELETE})
public class ExprRoleColour extends DiskordPropertyExpression<IRole, Color> {

	@Override
	protected Color[] get(Event event, IRole[] roles) {
		if (isNull(event)) return null;
		for (IRole role : roles) {
			collection.add(Color.byWoolColor(DyeColor.getByColor(org.bukkit.Color.fromRGB(role.getColor().getRGB()))));
		}
		return collection.toArray(new Color[collection.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		org.bukkit.Color color = ((Color)delta[0]).getBukkitColor();
		java.awt.Color colour = new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
		switch (mode) {
			case SET:
				for (IRole role : expressions.getAll(event, IRole.class)) {
					role.changeColor(colour);
				}
				break;
			case ADD:
			case REMOVE:
			case REMOVE_ALL:
				break;
			case DELETE:
			case RESET:
				for (IRole role : expressions.getAll(event, IRole.class)) {
					role.changeColor(java.awt.Color.WHITE);
				}
				break;
		}
	}
}
