package design_pattern.interpreter.scene01.common;

import java.util.HashMap;
/**
 抽象类非常简单，就一个方法 interpreter，负责对对传递进来的参数和值进行解析和匹配，其中输入
 参数为 HashMap 类型，key 值为模型中的参数，如 a、b、c 等，value 为运算时取得的具体数字。变量的解
 析器如下：
 */
public abstract class Expression {

	//解析公式和数值,其中var中的key值是是公式中的参数，value值是具体的数字
	public abstract int interpreter(HashMap<String, Integer> var);


	/**解释器模式的定义
	 * 解释器模式【Interpreter Pattern】是一种按照规定语法进行解析的模式，在现在项目中使
	 * 用较少（谁没事干会去写一个 PHP 或者 RUBY 的解析器），其定义如下：

	 AbstractExpression：抽象解释器，具体的解释任务由各个实现类完成，具体的解释器分为两大类：
	 TerminalExpression：终结符表达式，实现与文法中的元素相关联的解释操作，通常一个解释器模式
	 中只有一个终结符表达式，但有多个实例，对应不同的终结符。具体到我们例子就是 VarExpression 类，
	 表达式中的每个终结符都在堆栈中产生了一个 VarExpression 对象。
	 NonterminalExpression:非终结符表达式，文法中的每条规则对应一个非终结表达式，具体到我们的
	 例子就是加减法规则分别对应到 AddExpression 和 SubExpression 两个类。非终结符表达式根据逻辑的复
	 杂程度而增加，原则上每个文法规则都对应一个非终结符表达式。
	 Context:环境角色，具体到我们的例子中是采用 HashMap 代替。
	 */
}
