package me.limeglass.diskord.elements.expressions.category;

import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Changers;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.ICategory;

@Name("Category - NSFW")
@Description("Gets the NSFW state of the defined categories.")
@Properties({"discordcategories", "(NSFW|not safe for work) [state[s]]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[[the] categor(y|ies)]")
@Changers(ChangeMode.SET)
public class ExprCategoryNSFW extends DiskordPropertyExpression<ICategory, Boolean> {

	@Override
	protected Boolean[] get(Event event, ICategory[] categories) {
		if (isNull(event)) return null;
		for (ICategory category : categories) {
			collection.add(category.isNSFW());
		}
		return collection.toArray(new Boolean[collection.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (ICategory category : expressions.getAll(event, ICategory.class)) {
			category.changeNSFW((Boolean)delta[0]);
		}
	}
}
