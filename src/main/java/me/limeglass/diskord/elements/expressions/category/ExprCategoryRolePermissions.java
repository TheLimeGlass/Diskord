package me.limeglass.diskord.elements.expressions.category;

import java.util.HashSet;
import java.util.Set;

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
		Set<Permissions> permissions = new HashSet<Permissions>();
		for (ICategory category : expressions.getAll(event, ICategory.class)) {
			for (IRole role : expressions.getAll(event, IRole.class)) {
				permissions.addAll(category.getModifiedPermissions(role));
			}
		}
		return permissions.toArray(new Permissions[permissions.size()]);
	}
}