package com.lwhtarena.java8;

import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * <p>
 * <h2>简述：Java 8 Nashorn JavaScript</h2>
 * <ol></ol>
 * <h2>功能描述：Nashorn 一个 javascript 引擎。</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Java8NashornJavaScript {

    /**
     * Java 中调用 JavaScript
     * 使用 ScriptEngineManager, JavaScript 代码可以在 Java 中执行，实例如下：
     * **/
    @Test
    public void test001(){

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        String name = "Runoob";
        Integer result = null;

        try {
            nashorn.eval("print('" + name + "')");
            result = (Integer) nashorn.eval("10 + 2");

        }catch(ScriptException e){
            System.out.println("执行脚本错误: "+ e.getMessage());
        }

        System.out.println(result.toString());
    }

    /**JavaScript 中调用 Java
     * <pre>
     *     var BigDecimal = Java.type('java.math.BigDecimal');
     *
     * function calculate(amount, percentage) {
     *
     *    var result = new BigDecimal(amount).multiply(
     *    new BigDecimal(percentage)).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_EVEN);
     *
     *    return result.toPlainString();
     * }
     *
     * var result = calculate(568000000000000000023,13.9);
     * print(result);
     * </pre>
     * **/

}
