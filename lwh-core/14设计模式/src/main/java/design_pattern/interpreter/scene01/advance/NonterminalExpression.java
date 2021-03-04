package design_pattern.interpreter.scene01.advance;

/**通常终结符表达式比较简单，主要是处理场景元素和数据的转换。非终结表达式如下: **/
public class NonterminalExpression extends Expression {

	//每个非终结符表达式都会对其他表达式产生依赖
	public NonterminalExpression(Expression... expression){

	}

	public Object interpreter(Context ctx) {
		//进行文法处理
		return null;
	}

}
