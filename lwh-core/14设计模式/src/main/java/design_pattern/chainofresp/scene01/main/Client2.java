package design_pattern.chainofresp.scene01.main;

import design_pattern.chainofresp.scene01.inter.Handler;
import design_pattern.chainofresp.scene01.inter.IWomen;
import design_pattern.chainofresp.scene01.inter.impl.Father2;
import design_pattern.chainofresp.scene01.inter.impl.Husband2;
import design_pattern.chainofresp.scene01.inter.impl.Son2;
import design_pattern.chainofresp.scene01.inter.impl.Women;

import java.util.ArrayList;
import java.util.Random;

public class Client2 {

	public static void main(String[] args){
		//随机挑选女性
		Random random =new Random();
		ArrayList<IWomen> arrayList =new ArrayList<IWomen>();
		for(int i=0;i<5;i++){
			arrayList.add(new Women(random.nextInt(4), "我要出去逛街"));
		}


		//定义三个请示对象
		Handler father =new Father2();
		Handler husband =new Husband2();
		Handler son =new Son2();

		//设置请示顺序
		father.setNext(husband);
		husband.setNext(son);

		for(IWomen women:arrayList){
			father.handleMessage(women);
		}

	}

}
