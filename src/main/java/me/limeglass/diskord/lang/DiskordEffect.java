package me.limeglass.diskord.lang;


import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.event.Event;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.diskord.Diskord;
import me.limeglass.diskord.Syntax;

public abstract class DiskordEffect extends Effect implements DataChecker {

	protected ExpressionData expressions;
	protected int patternMark;
	
	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
		if (expressions != null && getSyntax() != null) this.expressions = new ExpressionData(expressions, getSyntax()[0]);
		this.patternMark = parser.mark;
		return true;
	}
	
	public String[] getSyntax() {
		return Syntax.get(getClass().getSimpleName());
	}
	
	@Override
	public String toString(Event event, boolean debug) {
		ArrayList<String> values = new ArrayList<String>();
		String modSyntax = Syntax.isModified(getClass()) ? "Modified syntax: " + Arrays.toString(getSyntax()) : Arrays.toString(getSyntax());
		if (event == null) {
			Diskord.debugMessage(getClass().getSimpleName() + " - " + modSyntax);
		} else {
			Arrays.asList(expressions.getExpressions()).stream().forEach(expression->values.add(expression.toString(event, debug)));
			Diskord.debugMessage(getClass().getSimpleName() + " - " + modSyntax + " (" + event.getEventName() + ")" + " Data: " + Arrays.toString(values.toArray()));
		}
		return Diskord.getNameplate() + getClass().getSimpleName() + "- Syntax: " + getSyntax();
	}
	
	public <V> Boolean isNull(Event event, Class<?>... types) {
		return isNull(event, expressions, types);
	}

	public Boolean isNull(Event event, int index) {
		return isNull(event, expressions, index);
	}

	public Boolean areNull(Event event) {
		return areNull(event, expressions);
	}
}