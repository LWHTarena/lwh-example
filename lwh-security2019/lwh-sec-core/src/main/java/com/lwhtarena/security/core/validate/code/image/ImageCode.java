package com.lwhtarena.security.core.validate.code.image;

import com.lwhtarena.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author liwh
 * @Title: ImageCode
 * @Package com.lwhtarena.security.core.validate.code.image
 * @Description: 图片验证码
 * @Version 1.0.0
 * @date 2020/7/20 20:23
 */
public class ImageCode extends ValidateCode {

    /**
     *
     */
    private static final long serialVersionUID = -6020470039852318468L;

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn){
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
