package com.lwhtarena.pxe.controller;

import com.lwhtarena.pxe.domain.IpmiBean;
import com.lwhtarena.pxe.domain.MsgVo;
import com.lwhtarena.pxe.domain.Net;
import com.lwhtarena.pxe.service.FileService;
import com.lwhtarena.pxe.service.IpmiService;
import com.lwhtarena.pxe.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/gather")
public class GatherController {

    public static final Logger infoLog = LoggerFactory.getLogger(GatherController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private IpmiService ipmiService;

    /**
     * 安装之前先检验虚拟机WIN的信息【收集虚拟机信息】
     * @return
     */
    @RequestMapping(value = "/vmInfo",method = RequestMethod.GET)
    @ResponseBody
    public MsgVo getVmInfo(){
        MsgVo msgVo =new MsgVo();

        Net net =fileService.gatherInfo();
        if(StringUtil.isNotNull(net)){
            msgVo.setCode("success");
            msgVo.setMsg("成功收集虚拟机信息！");
            msgVo.getDetail().put("net",net);
        }else{
            msgVo.setCode("error");
            msgVo.setMsg("收集虚拟机信息失败！");
            msgVo.getDetail().put("net",null);
        }
        return msgVo;
    }

    /**
     * 校验ipmi账号是否可用
     * @param bean
     * @return
     */
    @RequestMapping(value = "/testIpmi",method = RequestMethod.POST)
    public @ResponseBody MsgVo testIpmi(@RequestBody IpmiBean bean){
        MsgVo msgVo = new MsgVo();
        if(bean==null|| StringUtil.isNull(bean.getIpmiIP())
                ||StringUtil.isNull(bean.getPwd())
                ||StringUtil.isNull(bean.getUsername())){
            msgVo.setCode("f");
            return msgVo;
        }
        try {
            boolean  isConn  = ipmiService.testConnection(bean);
            if(isConn){
                ipmiService.reBootByPxe(bean);
                msgVo.setCode("s");
                return msgVo;//
            }
        } catch (Exception e) {
            infoLog.info(e.getMessage());
        }
        msgVo.setCode("f");
        return msgVo;
    }
}
