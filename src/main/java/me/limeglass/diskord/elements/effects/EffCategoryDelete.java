package me.limeglass.diskord.elements.effects;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.ICategory;

@Name("Category - Delete")
@Description("Deletes the defined categories.")
@Patterns("(remove|delete) [the] [discord] categor(y|ies) %discordcategories%")
public class EffCategoryDelete extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		for (ICategory category : expressions.getAll(event, ICategory.class)) {
			category.delete();
		}
	}
}