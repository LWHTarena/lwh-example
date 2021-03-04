package design_pattern.multition.scene01.main;

import design_pattern.multition.scene01.multition.Emperor;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 大臣们就悲惨了，一个皇帝都伺候不过来了，现在还来了两个皇帝
 * 随便找个皇帝，磕头请安就成了
 * </p>
 * @version:v1.0
 */
public class Minister {
    public static void main(String[] args) {
        int ministerNum =10;//10个大臣

        for(int i=0;i<ministerNum;i++){
            Emperor emperor = Emperor.getInstance();
            System.out.print("第"+(i+1)+"个大臣参拜的是：");
            emperor.emperorInfo();
        }

    }
}
