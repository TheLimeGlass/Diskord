package me.limeglass.diskord.elements.expressions.category;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IGuild;

@Name("Category - Guild")
@Description("Gets the guild (aka discord server) of the defined categories.")
@Properties({"discordcategories", "guild[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] categor(y|ies)")
public class ExprCategoryGuild extends DiskordPropertyExpression<ICategory, IGuild> {

	@Override
	protected IGuild[] get(Event event, ICategory[] categories) {
		if (isNull(event)) return null;
		for (ICategory category : categories) {
			collection.add(category.getGuild());
		}
		return collection.toArray(new IGuild[collection.size()]);
	}
}
