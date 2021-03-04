package design_pattern.mediator.scene01.common;

public class Client {

	public static void main(String[] args){
		//采购人员采购电脑
		System.out.println("------采购人员采购电脑-----");
		Purchase purchase =new Purchase();
		purchase.buyIBMcomputer(100);

		//销售人员销售电脑
		System.out.println("\n----------销售人员销售电脑-------------");
		Sale sale =new Sale();
		sale.sellIBMCompurter(1);

		//库存管理人员管理库存
		System.out.println("\n----------库存管理人员管理库存-----------");
		Stock stock =new Stock();
		stock.clearStock();
	}

}
