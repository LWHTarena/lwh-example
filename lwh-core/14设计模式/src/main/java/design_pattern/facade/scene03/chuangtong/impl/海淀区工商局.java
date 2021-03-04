package design_pattern.facade.scene03.chuangtong.impl;

import design_pattern.facade.scene03.chuangtong.工商局;

/**
 * Created by Administrator on 2017/2/26.
 */
public class 海淀区工商局 implements 工商局 {
    @Override
    public void checkName() {
        System.out.println("检查名字是否有冲突！");
    }
}
