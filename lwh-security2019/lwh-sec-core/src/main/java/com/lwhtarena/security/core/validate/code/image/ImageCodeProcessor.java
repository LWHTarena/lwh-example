package com.lwhtarena.security.core.validate.code.image;

import com.lwhtarena.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author liwh
 * @Title: ImageCodeProcessor
 * @Package com.lwhtarena.security.core.validate.code.image
 * @Description: 图片验证码处理器
 * @Version 1.0.0
 * @date 2020/7/20 20:25
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * 发送图形验证码，将其写到响应中
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

}

