package me.limeglass.diskord.elements;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.plugin.java.JavaPlugin;
import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import me.limeglass.diskord.Diskcord;
import me.limeglass.diskord.Metrics;
import me.limeglass.diskord.Syntax;
import me.limeglass.diskord.utils.EnumClassInfo;
import me.limeglass.diskord.utils.TypeClassInfo;
import me.limeglass.diskord.utils.annotations.*;

public class Register {
	
	public Set<Class<?>> classes = new HashSet<>();
	public Set<Class<?>> oldclasses = new HashSet<>(); 
	private JarFile Stocksaddon;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Register() {
		try {
			Method method = JavaPlugin.class.getDeclaredMethod("getFile");
			method.setAccessible(true);
			File file = (File) method.invoke(Diskcord.getInstance());
			Stocksaddon = new JarFile(file);
			for (Enumeration<JarEntry> jarEntry = Stocksaddon.entries(); jarEntry.hasMoreElements();) {
				String name = jarEntry.nextElement().getName().replace("/", ".");
				String className = name.substring(0, name.length() - 6);
				className = className.replace('/', '.');
				if (name.startsWith(Diskcord.getPackageName()) && name.endsWith(".class")) {
					classes.add(Class.forName(className));
				}
			}
			Stocksaddon.close();
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		run : for (Class clazz : classes) {
			if (!clazz.isAnnotationPresent(Disabled.class)) {
				String[] syntax = null;
				ExpressionType type = ExpressionType.COMBINED;
				if (clazz.isAnnotationPresent(Patterns.class)) {
					syntax = Syntax.register(clazz, ((Patterns)clazz.getAnnotation(Patterns.class)).value());
				} else if (PropertyExpression.class.isAssignableFrom(clazz) && clazz.isAnnotationPresent(Properties.class)) {
					type = ExpressionType.PROPERTY;
					String[] properties = ((Properties)clazz.getAnnotation(Properties.class)).value();
					String additions = (clazz.isAnnotationPresent(PropertiesAddition.class)) ? " " + ((PropertiesAddition) clazz.getAnnotation(PropertiesAddition.class)).value() + " " : " ";
					String input1 = "[the] ", input2 = "";
					if (properties.length > 2 && properties[2] != null) {
						int var = Integer.parseInt(properties[2].substring(1, 2));
						if (var == 1) input1 = properties[2].substring(3, properties[2].length());
						else input2 = properties[2].substring(3, properties[2].length());
					}
					String[] values = new String[]{input1 + " " + properties[1] + " of" + additions + "%" + properties[0] + "%", input2 + "%" + properties[0] + "%['s]"  + additions.replace("[the] ", "") + properties[1]};
					syntax = Syntax.register(clazz, values);
					if (syntax == null) Diskcord.debugMessage("&cThere was an issue registering the syntax for " + clazz.getName());
				} else {
					continue run;
				}
				if (clazz.isAnnotationPresent(RegisterEnum.class)) {
					try {
						String user = null;
						String enumType = ((RegisterEnum) clazz.getAnnotation(RegisterEnum.class)).value();
						Class returnType = ((RegisterEnum) clazz.getAnnotation(RegisterEnum.class)).ExprClass();
						if (returnType.equals(String.class)) returnType = ((Expression) clazz.newInstance()).getReturnType();
						if (clazz.isAnnotationPresent(User.class)) user = ((User) clazz.getAnnotation(User.class)).value();
						EnumClassInfo.create(returnType, enumType, user).register();
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				if (clazz.isAnnotationPresent(RegisterType.class)) {
					try {
						String user = null;
						String typeName = ((RegisterType) clazz.getAnnotation(RegisterType.class)).value();
						Class returnType = ((RegisterType) clazz.getAnnotation(RegisterType.class)).ExprClass();
						if (returnType.equals(String.class)) returnType = ((Expression) clazz.newInstance()).getReturnType();
						if (clazz.isAnnotationPresent(User.class)) user = ((User) clazz.getAnnotation(User.class)).value();
						TypeClassInfo.create(returnType, typeName, user).register();
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				if (syntax != null) {
					if (Effect.class.isAssignableFrom(clazz)) {
						Skript.registerEffect(clazz, syntax);
						Diskcord.debugMessage("&5Registered Effect " + clazz.getSimpleName() + " (" + clazz.getCanonicalName() + ") with syntax " + Arrays.toString(syntax));
					} else if (Condition.class.isAssignableFrom(clazz)) {
						Skript.registerCondition(clazz, syntax);
						Diskcord.debugMessage("&5Registered Condition " + clazz.getSimpleName() + " (" + clazz.getCanonicalName() + ") with syntax " + Arrays.toString(syntax));
					} else if (Expression.class.isAssignableFrom(clazz)) {
						if (clazz.isAnnotationPresent(ExpressionProperty.class)) type = ((ExpressionProperty) clazz.getAnnotation(ExpressionProperty.class)).value();
						try {
							Skript.registerExpression(clazz, ((Expression) clazz.newInstance()).getReturnType(), type, syntax);
							Diskcord.debugMessage("&5Registered Expression " + type.toString() + " " + clazz.getSimpleName() + " (" + clazz.getCanonicalName() + ") with syntax " + Arrays.toString(syntax));
						} catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
							Diskcord.consoleMessage("&cFailed to register expression " + clazz.getCanonicalName());
							e.printStackTrace();
						}
					}
				}
			}
		}
		new Events();
	}
	
	public static void metrics(Metrics metrics) {
		metrics.addCustomChart(new Metrics.SimplePie("skript_version") {
			@Override
			public String getValue() {
				return Skript.getVersion().toString();
			}
		});
		Diskcord.debugMessage("Metrics registered!");
	}
}