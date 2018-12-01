package me.limeglass.diskord.elements.effects;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordEffect;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.ICategory;

@Name("Category - Create voice channel")
@Description("Creates a voice channel in the defined categories(s).")
@Patterns("create [a] [discord] voice channel (with name|named) %string% in [categor(y|ies) %discordcategories%")
public class EffCategoryCreateVoiceChannel extends DiskordEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		String name = expressions.getSingle(event, String.class);
		for (ICategory category : expressions.getAll(event, ICategory.class)) {
			category.createVoiceChannel(name);
		}
	}
}
