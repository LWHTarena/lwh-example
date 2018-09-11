package com.lwhtarena.pxe.controller;

import ch.qos.logback.core.util.FileUtil;
import com.lwhtarena.pxe.service.FileService;
import com.lwhtarena.pxe.service.HostService;
import com.lwhtarena.pxe.service.IpmiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * <h2>简述：自动化部署</h2>
 * <ol></ol>
 * <h2>功能描述：自动化部署</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */

@RestController
@EnableAutoConfiguration
@RequestMapping("/authInstall")
public class AuthInstallController {

//    public static final Logger infoLog = LoggerFactory.getLogger(AuthInstallController.class);


    @Autowired
    private HostService hostService;

    @Autowired
    private FileService fileService;

    @Autowired
    private IpmiService ipmiService;

    @Autowired
    private FileUtil fileUtil;

    //todo 安装之前先检验虚拟机WIN的信息



    //todo 步骤一

    //todo 步骤二

    //todo 步骤三

    //todo 步骤四

    //todo 步骤五

    //todo 步骤六



}
