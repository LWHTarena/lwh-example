package design_pattern.mediator.scene01.advance;

/**
 * 中介者模式【Mediator Pattern】
 * @author liwenhao
 *
 */
public class Purchase extends AbstractColleague{

	public Purchase(AbstactMediator _mediator){
		super(_mediator);
	}
	//采购IBM型号的电脑
	public void buyIBMcomputer(int number){
		super.mediator.execute("purchase.buy", number);
	}

	//不再采购IBM电脑
	public void refuseBuyIBM(){
		System.out.println("不再采购IBM电脑");
	}

}
