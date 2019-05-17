package com.lwhtarena.security.aes.controller;

import com.lwhtarena.security.aes.service.AesService;
import com.lwhtarena.security.aes.utils.AesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
@Controller
public class AesController {
    @Autowired
    private AesService aesService;

    /**
     * 加密逻辑为： name+password 使用key作为密匙源，加密得到securityMessage
     * @param key 密匙源
     * @param securityMessage 加密后的签名
     * @param name 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping("/testAes")
    @ResponseBody
    public AesResponse testDes(@RequestParam("key") String key, @RequestParam("message") String securityMessage
            , @RequestParam("name") String name, @RequestParam("password") String password){

        AesResponse resp = this.aesService.testAes(key, securityMessage, name, password);

        return resp;
    }
}
