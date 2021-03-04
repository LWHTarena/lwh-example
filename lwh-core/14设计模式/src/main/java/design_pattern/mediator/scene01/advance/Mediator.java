package design_pattern.mediator.scene01.advance;

/**
 * 中介者模式【Mediator Pattern】
 * @author liwenhao
 *
 */
public class Mediator extends AbstactMediator {

	//中介者最重要的方法
	@Override
	public void execute(String str, Object... objects) {
		if(str.equals("purchase.buy")){//采购电脑
			this.buyComputer((Integer)objects[0]);
		}else if(str.equals("sale.sell")){//销售电脑
			this.sellCompter((Integer)objects[0]);
		}else if(str.equals("sale.offsell")){//折价销售
			this.offsell();
		}else if(str.equals("stock.clear")){//清仓处理
			this.clearStock();
		}

	}

	//采购电脑
	private void buyComputer(int number) {
		int saleStatus =super.sale.getSaleStatus();
		if(saleStatus>80){//销售情况良好
			System.out.println("采购IBM电脑："+number+"台");
			stock.increase(number);
		}else{//销售情况不好
			int buyNumber =number/2;//折半采购
			System.out.println("采购IBM电脑："+buyNumber+"台");
		}

	}

	//销售电脑
	private void sellCompter(int number) {
		if(super.stock.getStockNumber()<number){//库存数量不够销售
			super.purchase.buyIBMcomputer(number);
		}
		super.stock.decrease(number);

	}

	private void offsell() {
		System.out.println("折价销售IBM电脑"+stock.getStockNumber()+"台");
	}

	private void clearStock() {
		//要求清仓销售
		super.sale.offSale();

		//要求采购人员不要采购
		super.purchase.refuseBuyIBM();

	}



}
