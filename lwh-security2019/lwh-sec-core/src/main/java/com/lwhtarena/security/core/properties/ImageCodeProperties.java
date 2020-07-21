package com.lwhtarena.security.core.properties;

/**
 * @author liwh
 * @Title: ImageCodeProperties
 * @Package com.lwhtarena.security.properties
 * @Description: 图片验证码配置项…
 * @Version 1.0.0
 * @date 2020/7/20 16:06
 */
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    /**
     * 图片宽
     */
    private int width = 67;
    /**
     * 图片高
     */
    private int height = 23;

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

}
