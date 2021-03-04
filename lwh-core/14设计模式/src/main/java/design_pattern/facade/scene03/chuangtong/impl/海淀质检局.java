package design_pattern.facade.scene03.chuangtong.impl;

import design_pattern.facade.scene03.chuangtong.质检局;

/**
 * Created by Administrator on 2017/2/26.
 */
public class 海淀质检局 implements 质检局 {
    @Override
    public void orgCodeCertificate() {
        System.out.println("在海淀区质检局办理组织机构代码证！");
    }
}
