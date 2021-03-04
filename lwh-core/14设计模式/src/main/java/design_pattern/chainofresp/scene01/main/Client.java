package design_pattern.chainofresp.scene01.main;

import design_pattern.chainofresp.scene01.inter.IHandler;
import design_pattern.chainofresp.scene01.inter.IWomen;
import design_pattern.chainofresp.scene01.inter.impl.Father;
import design_pattern.chainofresp.scene01.inter.impl.Husband;
import design_pattern.chainofresp.scene01.inter.impl.Son;
import design_pattern.chainofresp.scene01.inter.impl.Women;

import java.util.ArrayList;
import java.util.Random;

public class Client {

	public static void main(String[] args){
		//随机挑选女性
		Random random =new Random();
		ArrayList<IWomen> arrayList =new ArrayList<IWomen>();
		for(int i=0;i<5;i++){
			arrayList.add(new Women(random.nextInt(4), "我要出去逛街"));
		}


		//定义三个请示对象
		IHandler father =new Father();
		IHandler husband =new Husband();
		IHandler son =new Son();

		for(IWomen women:arrayList){
			if(women.getType()==1){//未婚少女

				System.out.println("\n--------女儿向父亲请示----------");
				father.handleMessage(women);
			}else if(women.getType()==2){//已婚少妇

				System.out.println("\n--------妻子向丈夫请示----------");
				husband.handleMessage(women);
			}else if(women.getType()==3){//母亲

				System.out.println("\n--------母亲向儿子请示----------");
				son.handleMessage(women);
			}else{
				//暂时啥也不做
			}

		}

	}

}
