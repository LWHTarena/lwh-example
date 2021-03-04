package design_pattern.interpreter.scene01.advance;

/**
 * 抽象表达式是生成语法集合（也叫做语法树）的关键，每个语法集合完成指定语法解析任务，它是通
 过迭代调用的方式，最终由最小的语法单元进行解析完成。终结符表达式如下：
 */
public class TerminalExpression extends Expression {

	//通常终结符表达式只有一个，但是有多个对象
	public Object interpreter(Context ctx) {
		return null;
	}
}
