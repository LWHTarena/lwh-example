package com.lwhtarena.code;

import com.lwhtarena.security.core.validate.code.ValidateCodeGenerator;
import com.lwhtarena.security.core.validate.code.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author liwh
 * @Title: DemoImageCodeGenerator
 * @Package com.lwhtarena.code
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:24
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    /* (non-Javadoc)
     * @see com.imooc.security.core.validate.code.ValidateCodeGenerator#generate(org.springframework.web.context.request.ServletWebRequest)
     */
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }

}