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

@Name("Category - Position")
@Description("Gets the position of the defined categories.")
@Properties({"discordcategories", "position[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[the] categor(y|ies)")
@Changers(ChangeMode.SET)
public class ExprCategoryPosition extends DiskordPropertyExpression<ICategory, Number> {

	@Override
	protected Number[] get(Event event, ICategory[] categories) {
		if (isNull(event)) return null;
		for (ICategory category : categories) {
			collection.add(category.getPosition());
		}
		return collection.toArray(new Number[collection.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (ICategory category : expressions.getAll(event, ICategory.class)) {
			category.changePosition(((Number)delta[0]).intValue());
		}
	}
}
