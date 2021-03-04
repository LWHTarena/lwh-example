package design_pattern.mediator.scene01.advance;

/**中介者模式有以下几部分:
 * 抽象中介者（Mediator）角色：抽象中介者角色定义统一的接口用于各种同事角色之间的通信
 * 具体中介者（Concrete Mediator）模式：具体中介者角色通过协调各种同事角色实现协作行为，因此它依赖于各个同事角色
 * 同事（Colleague）角色:
 *
 * @author liwenhao
 *
 */
public class Client {

	public static void main(String[] args){
		AbstactMediator mediator =new Mediator();
		//采购人员采购电脑
		System.out.println("------采购人员采购电脑-----");
		Purchase purchase =new Purchase(mediator);
		purchase.buyIBMcomputer(100);

		//销售人员销售电脑
		System.out.println("\n----------销售人员销售电脑-------------");
		Sale sale =new Sale(mediator);
		sale.sellIBMCompurter(1);

		//库存管理人员管理库存
		System.out.println("\n----------库存管理人员管理库存-----------");
		Stock stock =new Stock(mediator);
		stock.clearStock();
	}

}
