package me.limeglass.diskord;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.util.SimpleEvent;
import me.limeglass.diskord.utils.Utils;
import me.limeglass.diskord.utils.annotations.AllChangers;
import me.limeglass.diskord.utils.annotations.AntiDependency;
import me.limeglass.diskord.utils.annotations.Changers;
import me.limeglass.diskord.utils.annotations.Disabled;

public class Syntax {

	private static HashMap<String, String[]> completeSyntax = new HashMap<>();
	private static HashMap<String, String[]> modified = new HashMap<>();

	public static String[] register(Class<?> syntaxClass, String... syntax) {
		if (syntaxClass.isAnnotationPresent(Disabled.class)) return null;
		String type = "Expressions";
		if (Condition.class.isAssignableFrom(syntaxClass)) type = "Conditions";
		else if (Effect.class.isAssignableFrom(syntaxClass)) type = "Effects";
		else if (SimpleEvent.class.isAssignableFrom(syntaxClass)) type = "Events";
		else if (PropertyExpression.class.isAssignableFrom(syntaxClass)) type = "PropertyExpressions";
		String node = "Syntax." + type + "." + syntaxClass.getSimpleName() + ".";
		if (!Diskord.getSyntaxData().isSet(node + "enabled")) {
			Diskord.getSyntaxData().set(node + "enabled", true);
			save();
		}
		if (syntaxClass.isAnnotationPresent(Changers.class) || syntaxClass.isAnnotationPresent(AllChangers.class)) {
			if (syntaxClass.isAnnotationPresent(AllChangers.class)) Diskord.getSyntaxData().set(node + "changers", "All changers");
			else {
				ChangeMode[] changers = syntaxClass.getAnnotation(Changers.class).value();
				Diskord.getSyntaxData().set(node + "changers", Arrays.toString(changers));
			}
			save();
		}
		if (syntaxClass.isAnnotationPresent(AntiDependency.class)) {
			String plugin = ((AntiDependency) syntaxClass.getAnnotation(AntiDependency.class)).value()[0];
			if (Bukkit.getPluginManager().getPlugin(plugin) != null && Bukkit.getPluginManager().getPlugin(plugin).isEnabled()) return null;
		}
		if (syntaxClass.isAnnotationPresent(Description.class)) {
			String[] descriptions = syntaxClass.getAnnotation(Description.class).value();
			Diskord.getSyntaxData().set(node + "description", descriptions[0]);
			save();
		}
		if (!Diskord.getSyntaxData().getBoolean(node + "enabled")) {
			if (Diskord.getInstance().getConfig().getBoolean("NotRegisteredSyntax", false)) Diskord.consoleMessage(node.toString() + " didn't register!");
			return null;
		}
		if (!Diskord.getSyntaxData().isSet(node + "syntax")) {
			Diskord.getSyntaxData().set(node + "syntax", syntax);
			save();
			return add(syntaxClass.getSimpleName(), syntax);
		}
		List<String> data = Diskord.getSyntaxData().getStringList(node + "syntax");
		if (!Utils.compareArrays(data.toArray(new String[data.size()]), syntax)) modified.put(syntaxClass.getSimpleName(), syntax);
		if (Diskord.getSyntaxData().isList(node + "syntax")) {
			List<String> syntaxes = Diskord.getSyntaxData().getStringList(node + "syntax");
			return add(syntaxClass.getSimpleName(), syntaxes.toArray(new String[syntaxes.size()]));
		}
		return add(syntaxClass.getSimpleName(), new String[]{Diskord.getSyntaxData().getString(node + "syntax")});
	}
	
	public static Boolean isModified(Class<?> syntaxClass) {
		return modified.containsKey(syntaxClass.getSimpleName());
	}
	
	public static String[] get(String syntaxClass) {
		return completeSyntax.get(syntaxClass);
	}
	
	private static String[] add(String syntaxClass, String... syntax) {
		if (!completeSyntax.containsValue(syntax)) {
			completeSyntax.put(syntaxClass, syntax);
		}
		return syntax;
	}
	
	public static void save() {
		try {
			Diskord.getSyntaxData().save(Diskord.syntaxFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
