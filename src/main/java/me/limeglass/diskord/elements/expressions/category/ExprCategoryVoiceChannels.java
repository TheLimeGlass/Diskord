package me.limeglass.diskord.elements.expressions.category;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.diskord.lang.DiskordPropertyExpression;
import me.limeglass.diskord.utils.annotations.Properties;
import me.limeglass.diskord.utils.annotations.PropertiesAddition;
import sx.blah.discord.handle.obj.ICategory;
import sx.blah.discord.handle.obj.IVoiceChannel;

@Name("Category - Voice Channels")
@Description("Gets the voice channels of the defined categories.")
@Properties({"discordcategories", "voice channel[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[[the] categor(y|ies)]")
public class ExprCategoryVoiceChannels extends DiskordPropertyExpression<ICategory, IVoiceChannel> {

	@Override
	protected IVoiceChannel[] get(Event event, ICategory[] categories) {
		if (isNull(event)) return null;
		Set<IVoiceChannel> channels = new HashSet<IVoiceChannel>();
		for (ICategory category : categories) {
			channels.addAll(category.getVoiceChannels());
		}
		return channels.toArray(new IVoiceChannel[channels.size()]);
	}
}