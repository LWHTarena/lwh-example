package com.lwhtarena.security.core.properties;

/**
 * @author liwh
 * @Title: ValidateCodeProperties
 * @Package com.lwhtarena.security.properties
 * @Description: 验证码配置
 * @Version 1.0.0
 * @date 2020/7/20 16:25
 */
public class ValidateCodeProperties {
    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();
    /**
     * 短信验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }
}
