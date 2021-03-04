package design_pattern.facade.scene03.chuangtong.impl;

import design_pattern.facade.scene03.chuangtong.税务局;

/**
 * Created by Administrator on 2017/2/26.
 */
public class 海淀税务局 implements 税务局 {
    @Override
    public void taxCertificate() {
        System.out.println("在海淀税务局办理税务登记证！");
    }
}
