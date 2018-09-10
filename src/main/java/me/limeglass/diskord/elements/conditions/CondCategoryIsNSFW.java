package me.limeglass.diskord.elements.conditions;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordCondition;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.ICategory;

@Name("Category - Is NSFW")
@Description("Check if a category is NSFW (not safe for work).")
@Patterns("[category] %discordcategory% (1¦is|2¦is(n't| not)) (NSFW|not safe for work)")
public class CondCategoryIsNSFW extends DiskordCondition {

	public boolean check(Event event) {
		if (areNull(event)) return false;
		return (expressions.getSingle(event, ICategory.class).isNSFW()) ? isNegated() : !isNegated();
	}
}