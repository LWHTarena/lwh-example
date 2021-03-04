package design_pattern.adapter.scene02.main;

import design_pattern.adapter.scene02.inter.*;
import design_pattern.adapter.scene02.inter.impl.NewClass;

public class MyTest {

    public static void main(String[] args) {
        NewInterface ni =new NewClass();

        //老功能
        ni.eat();

        //新需求
        ni.sleep();

        System.out.println("++++++++++++++++++++++++++++++++++");

        NewInterface2 ni2 =new ObjectAdapter(ni);
        ni2.sleep();

        System.out.println("++++++++++++++++++++++++++++++++++");

        NewInterface3 ni3 =new NewInterfClass();
        ni3.sleep();
    }
}
