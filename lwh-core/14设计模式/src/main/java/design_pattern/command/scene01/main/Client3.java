package design_pattern.command.scene01.main;

import design_pattern.command.scene01.command.Command;
import design_pattern.command.scene01.command.DeletePageCommand;
import design_pattern.command.scene01.invoker.Invoker;

/**
 * 客户就是甲方,给我们前的一方,是老大
 * Receiver角色：这个就是干活的角色。具体到上面我们的例子中就是Croup的三个实现类
 * Commend角色：就是命令，需要我执行的所有命令都这里声明
 * Invoker角色：调用者，接收到命令，例如项目经理就是这个角色
 *
 * 命令模式比较简单，但是在项目中使用时非常频繁的，封装性非常好，因为她把请求方（Invoker）
 * 和执行方（Receiver）分开了，扩展性也有很好的保障。
 * 但是命令模式也是有缺点的，你看Command的子类没有，那个如果我们写下去可不是几个，而是几十个，
 * 这个类膨胀的非常多，这个需要大家在项目中自己考虑使用了。
 */
public class Client3 {
	public static void main(String[] args){
		//定义我们的接头人
		Invoker xiaoSan =new Invoker();//接头人就是小三

		System.out.println("Client.main()------客户要求删除一项需求-----");
		//接头人就是小三
		Command command =new DeletePageCommand();
		//接头人接受到命令
		xiaoSan.setCommand(command);
		//接头人执行命令
		xiaoSan.action();


	}


}
