package com.lwhtarena.选择题;

import java.util.HashSet;
import java.util.Set;

public class $_3 {

	public static void main(String[] args) {
		Set<Short> s=new HashSet<Short>();
		for(Short i=0;i<100;i++)
		{
			s.add(i);
			System.out.println(s);
			s.remove(i-1);
		}
		System.out.println(s.size());

	}

}
