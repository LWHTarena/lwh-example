package design_pattern.command.scene01.command;


import design_pattern.command.scene01.abstraction.CodeGroup;
import design_pattern.command.scene01.abstraction.PageGroup;
import design_pattern.command.scene01.abstraction.RequirementGroup;

/**
 * 命令模式 【Command Pattern】
 * 命令的抽象类，我们把客户发出的命令定义成
 * 一个一个的对象
 */
public abstract class Command {

    //把三个组都定义好，子类可以直接使用
    protected RequirementGroup rg =
            new RequirementGroup();//需求组
    protected PageGroup pg = new PageGroup();//美工组
    protected CodeGroup cg = new CodeGroup();

    //只要一个方法，你要我做什么事情
    public abstract void execute();

}
