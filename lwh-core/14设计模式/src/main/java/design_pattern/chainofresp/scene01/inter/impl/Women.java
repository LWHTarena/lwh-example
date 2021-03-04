package design_pattern.chainofresp.scene01.inter.impl;

import design_pattern.chainofresp.scene01.inter.IWomen;

public class Women implements IWomen {

	/* 通过一个int类型参数来描述妇女的个人状况
	 *  1---未出嫁
	 *  2---出嫁
	 *  3---夫死
	 */

	private int type =0;

	//妇女的请示
	private String request ="";

	//通过构造函数传递过来请求
	public Women(int _type,String _request){
		this.type =_type;
		this.request =_request;
	}

	@Override
	public int getType() {
		return this.type;
	}

	@Override
	public String getRequest() {
		return this.request;
	}

}