package com.lwhtarena.注解;


import java.lang.annotation.*;

/*
 * 码农定义注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Programmer{
    String value() default "马云";
}
/**
 * 码农类型注解
 * @author peida
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface ProgrammerType {
    /**
     * 类型枚举 程序猿 射鸡师
     */
    public enum CoderType{MONKEYS,LION,CHOOK};
    /**
     * 颜色属性
     */
    CoderType type() default CoderType.MONKEYS;
}
/**
 * 码农制造厂
 * @author Administrator
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface ProgrammerProductor {
    /**
     * 厂家编号
     * @return
     */
    public int id() default -1;
    /**
     * 厂家名称
     * @return
     */
    public String name() default "shsxt";
    /**
     * 厂家地址
     * @return
     */
    public String address() default "上海";
}

/**
 * 注解使用
 */
public class Coder {

    @Programmer("老裴")
    private String coderName;

    @ProgrammerType(type= ProgrammerType.CoderType.MONKEYS)

    private String coderType;
    @ProgrammerProductor(id=1,name="程序猿乐园",address="荣乐东路")

    private String coderProductor;

    public String getCoderName() {
        return coderName;
    }
    public void setCoderName(String coderName) {
        this.coderName = coderName;
    }
    public String getCoderType() {
        return coderType;
    }
    public void setCoderType(String coderType) {
        this.coderType = coderType;
    }
    public String getCoderProductor() {
        return coderProductor;
    }
    public void setCoderProductor(String coderProductor) {
        this.coderProductor = coderProductor;
    }
}
