package design_pattern.interpreter.scene01.common;

import java.util.HashMap;

/*变量解析器*/
public class VarExpression extends Expression {

	private String key;
	public VarExpression(String _key){
		this.key =_key;
	}

	@Override//从map中取得
	public int interpreter(HashMap<String, Integer> var) {
		return var.get(this.key);
	}

}
