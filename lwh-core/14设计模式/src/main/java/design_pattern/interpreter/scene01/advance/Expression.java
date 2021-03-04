package design_pattern.interpreter.scene01.advance;
/**
 * 解释器是一个比较少用的模式，以下为其通用源码，可以做为参考。抽象表达式通常只有一个方法，如下：
 */
public abstract class Expression {
	//每个表达式必须有一个解析任务
	public abstract Object interpreter(Context ctx);
}