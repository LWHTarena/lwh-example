package com.lwhtarena.pxe.controller;

import com.lwhtarena.pxe.domain.*;
import com.lwhtarena.pxe.service.FileService;
import com.lwhtarena.pxe.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api("超融合为自动化部署步骤一")
@RestController
@EnableAutoConfiguration
@RequestMapping("/first")
public class FirstController {

    public static final Logger infoLog = LoggerFactory.getLogger(FirstController.class);

    @Autowired
    private FileService fileService;

    /**
     * 安装之前先检验虚拟机WIN的信息【收集虚拟机信息】
     * @return
     */
    @ApiOperation("回显win服务网卡信息")
    @RequestMapping(value = "/vmInfo",method = RequestMethod.GET)
    @ResponseBody
    public MsgVo getVmInfo(){
        MsgVo msgVo =new MsgVo();

        Net net =fileService.gatherInfo();
        if(StringUtil.isNotNull(net)){
            msgVo.setCode(MsgCodeEnum.sucess.getCode());
            msgVo.setMsg("成功收集虚拟机信息！");
            msgVo.getDetail().put("net",net);
        }else{
            msgVo.setCode(MsgCodeEnum.fail.getCode());
            msgVo.setMsg("收集虚拟机信息失败！");
            msgVo.getDetail().put("net",null);
        }
        return msgVo;
    }

    /**
     * 第一步：准备安装环境
     * 请根据当前网络环境修改WIM虚拟机IP（修改虚拟机服务ip，这里指定可用静态ip）
     *
     * @return
     * @author：liwh
     */
    @ApiOperation("修改WIN网卡信息")
    @RequestMapping(value = "/modifyEth", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MsgVo modifyLocalServce(@RequestBody Net net){

        //todo 后端做校验
        MsgVo msgVo = new MsgVo();

        if(StringUtil.isNotNull(net)){
            Task task =net.getTask();
            if(StringUtil.isNull(task)){
                task =new Task();
                task.setTaskId(StringUtil.getUUID());
                task.setTaskName("部署任务"+task.getTaskId());
            }
            task.setInStep(2);
            net.setTask(task);

            boolean bool = fileService.modifyLocalServce(net);
            if (bool) {
                msgVo.setCode(MsgCodeEnum.sucess.getCode());
                msgVo.setMsg("修改成功，虚拟机通信ip：" + net.getIp());
                msgVo.getDetail().put("net",net);
                msgVo.getDetail().put("task",net.getTask());
            }

        }else{
            msgVo.setCode(MsgCodeEnum.msg1001.getCode());
            msgVo.setMsg(MsgCodeEnum.msg1001.getValue());
            msgVo.getDetail().put("net",net);
        }
        return msgVo;

    }

}
