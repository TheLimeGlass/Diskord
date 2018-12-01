package me.limeglass.diskord.elements.expressions.category;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordExpression;
import me.limeglass.diskord.utils.annotations.Patterns;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.Permissions;

@Name("Category - Role Permissions")
@Description("Returns the permissions of roles in the defined categories.")
@Patterns("permissions (from|of) [discord] role[s] %discordroles% (within|from|in) [discord] categor(y|ies) %discordcategories%")
public class ExprCategoryRolePermissions extends DiskordExpression<Permissions> {
	
	@Override
	protected Permissions[] get(Event event) {
		if (areNull(event)) return null;
		IRole[] roles = expressions.getAll(event, IRole.class);
		for (ICategory category : expressions.getAll(event, ICategory.class)) {
			for (IRole role : roles) {
				collection.addAll(category.getModifiedPermissions(role));
			}
		}
		return collection.toArray(new Permissions[collection.size()]);
	}
}
