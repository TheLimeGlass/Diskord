package me.limeglass.diskord.elements.expressions.category;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IChannel;

@Name("Category - Channels")
@Description("Gets the channels of the defined categories.")
@Properties({"discordcategories", "channel[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] categor(y|ies)")
public class ExprCategoryChannels extends DiskordPropertyExpression<ICategory, IChannel> {

	@Override
	protected IChannel[] get(Event event, ICategory[] categories) {
		if (isNull(event)) return null;
		for (ICategory category : categories) {
			collection.addAll(category.getChannels());
		}
		return collection.toArray(new IChannel[collection.size()]);
	}
}
