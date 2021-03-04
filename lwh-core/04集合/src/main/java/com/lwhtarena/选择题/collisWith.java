package com.lwhtarena.选择题

����;
/**
 * .��д����ʵ��ͬһƽ������Բ�Ƿ���ײ�����У�
                 ��һ��ԲԲ������Ϊ(x1,y1)���뾶�� r1���ڶ���ԲԲ������Ϊ(x2,y2)���뾶�� r2��
                  �����������£�
            boolean collisWith(int x1,int y1,int r1,int x2,int y2,int r2){}
 * @author liwenhao
 *
 */
public class collisWith {
	boolean collisWith(int  x1, int y1, int r1, int x2, int y2, int r2) {
		boolean flag=false;
		int num1=(x1-x2)*(x1-x2);
		int num2=(y1-y2)*(y1-y2);
		int num3=num1+num2;
		double distance=Math.sqrt(num3);
		if(distance<=(r1+r2)){
		flag=true;
		}
		return flag;
		}
}
