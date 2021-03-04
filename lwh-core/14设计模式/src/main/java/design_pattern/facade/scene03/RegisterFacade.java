package design_pattern.facade.scene03;

import design_pattern.facade.scene03.chuangtong.impl.中国工商银行;
import design_pattern.facade.scene03.chuangtong.impl.海淀区工商局;
import design_pattern.facade.scene03.chuangtong.impl.海淀税务局;
import design_pattern.facade.scene03.chuangtong.impl.海淀质检局;
import design_pattern.facade.scene03.chuangtong.工商局;
import design_pattern.facade.scene03.chuangtong.税务局;
import design_pattern.facade.scene03.chuangtong.质检局;
import design_pattern.facade.scene03.chuangtong.银行;

/**
 * Created by Administrator on 2017/2/26.
 */
public class RegisterFacade {
    public void register(){
        工商局 a = new 海淀区工商局();
        a.checkName();
        质检局 b = new 海淀质检局();
        b.orgCodeCertificate();
        税务局 c  = new 海淀税务局();
        c.taxCertificate();
        银行 d = new 中国工商银行();
        d.openAccount();
    }
}
