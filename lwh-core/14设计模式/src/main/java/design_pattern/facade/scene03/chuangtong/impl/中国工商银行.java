package design_pattern.facade.scene03.chuangtong.impl;

import design_pattern.facade.scene03.chuangtong.银行;

/**
 * Created by Administrator on 2017/2/26.
 */
public class 中国工商银行 implements 银行 {
    @Override
    public void openAccount() {
        System.out.println("在中国工商银行开户！");
    }
}
