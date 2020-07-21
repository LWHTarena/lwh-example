package com.lwhtarena.security.core.validate.code.sms;

/**
 * @author liwh
 * @Title: SmsCodeSender
 * @Package com.lwhtarena.security.core.validate.code.sms
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 20:45
 */
public interface SmsCodeSender {

    /**
     * @param mobile
     * @param code
     */
    void send(String mobile, String code);

}
