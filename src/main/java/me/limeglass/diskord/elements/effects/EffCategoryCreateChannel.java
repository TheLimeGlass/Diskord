package me.limeglass.diskord.elements.effects;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.ICategory;

@Name("Category - Create channel")
@Description("Creates a channel in the defined categories(s).")
@Patterns("create [a] [discord] channel (with name|named) %string% in [categor(y|ies) %discordcategories%")
public class EffCategoryCreateChannel extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		for (ICategory category : expressions.getAll(event, ICategory.class)) {
			category.createChannel(expressions.getSingle(event, String.class));
		}
	}
}