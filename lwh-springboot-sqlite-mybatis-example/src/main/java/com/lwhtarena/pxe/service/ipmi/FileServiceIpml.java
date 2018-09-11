package com.lwhtarena.pxe.service.ipmi;

import com.lwhtarena.pxe.domain.*;
import com.lwhtarena.pxe.service.FileService;
import com.lwhtarena.pxe.service.GroupService;
import com.lwhtarena.pxe.service.TaskService;
import com.lwhtarena.pxe.util.FileUtil;
import com.lwhtarena.pxe.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fileService")
public class FileServiceIpml implements FileService {

    @Autowired
    FileUtil fileUtil;

    @Autowired
    GroupService groupService;

    @Autowired
    TaskService taskService;

    @Override
    public MsgVo createAndModify(List<Config> configs) throws Exception {
        MsgVo msgVo =new MsgVo();

        String ip = FileUtil.gatherServeIp();


        /***********************************************************
         * 两个主要目录：
         *    1、pxe配置文件统一存放：/var/lib/tftpboot/pxelinux.cfg
         *    2、ks应答文件统一存放：/nfs/WinServer
         *
         *  创建配置文件：
         *
         *    vi /var/lib/tftpboot/pxelinux.cfg/01-xxx  --> 存放在 /vi /var/lib/tftpboot/pxelinux.cfg
         *
         *  ks文件：
         *    cp winserver65-auto.ks   01-xxxx (这里大写) --> 存放在 /nfs/WinServer
         *
         * 注意事项：主机名不能重复
         *
         **********************************************************/
        List<String> error = new ArrayList<String>();
        if(StringUtil.isNotEmpty(ip) && configs.size()>0){

            for(Config conf:configs){

                try {
                    fileUtil.createPXE(conf,ip);
                }catch (Exception e){
                    error.add("创建pxe文件失败。（物理机ip:"+ip+")");
                }

                try {
                    fileUtil.createKS(conf,ip);
                }catch (Exception e){
                    error.add("创建KS文件失败。（物理机ip:"+ip+")");
                }
            }

        }else{
            error.add("创建失败，请检查应答服务器和配置文件！ 收集dhcp的服务ip == tftp服务ip == nfs 服务ip ==null");
        }


        if(error != null && error.size()>0){
            msgVo .setCode("f");
            msgVo.setMsg("创建失败，请检查应答服务器和配置文件！");
            msgVo.getDetail().put("createFileError",error);
        }else{
            msgVo .setCode("s");
            msgVo.setMsg("成功创建并修改应答文件！");
        }
        return msgVo;
    }

    /**
     * 创建单个文件
     * @param taskId
     * @param hostId
     * @return
     * @throws Exception
     */
    @Override
    public MsgVo createFile(String taskId, String hostId) throws Exception {
        MsgVo msgVo =new MsgVo();


        InstallTask it =new InstallTask();
        it.setTaskId(taskId);
        InstallTask task = taskService.selectByEntity(it);
        List<Config> configs =task.getHosts();

        String ip =FileUtil.gatherServeIp();
        List<String> error = new ArrayList<String>();
        if(StringUtil.isNotEmpty(ip) && configs.size()>0){
            for(Config conf:configs){
                if(hostId.equals(conf.getHostId())){
                    try {
                        fileUtil.createPXE(conf,ip);
                    }catch (Exception e){
                        error.add("创建pxe文件失败。（物理机ip:"+ip+")");
                    }

                    try {
                        fileUtil.createKS(conf,ip);
                    }catch (Exception e){
                        error.add("创建KS文件失败。（物理机ip:"+ip+")");
                    }
                }
            }
        }else{
            error.add("创建失败，请检查应答服务器和配置文件！ 收集dhcp的服务ip == tftp服务ip == nfs 服务ip ==null");
        }
        if(error != null && error.size()>0){
            msgVo .setCode("f");
            msgVo.setMsg("创建失败，请检查应答服务器和配置文件！");
            msgVo.getDetail().put("createFileError",error);
        }else{
            msgVo .setCode("s");
            msgVo.setMsg("成功创建并修改应答文件！");
        }
        return msgVo;
    }

    /**
     * 删除单个文件
     * @param taskId
     * @param hostId
     * @return
     * @throws Exception
     */
    @Override
    public MsgVo delFile(String taskId, String hostId) throws Exception {
        MsgVo msgVo =new MsgVo();

        InstallTask it =new InstallTask();
        it.setTaskId(taskId);
        InstallTask task =taskService.selectByEntity(it);
        List<Config> configs =task.getHosts();

        String ip =FileUtil.gatherServeIp();
//        FileUtil fileUtil =new FileUtil();

        if(StringUtil.isNotEmpty(ip) && configs.size()>0){
            for(Config conf:configs){
                if(hostId.equals(conf.getHostId())){
                    try {
                        boolean bool =fileUtil.deleteFile(conf);
                         if(bool){
                             msgVo.setMsg("删除文件成功！)");
                             msgVo.setCode("error");
                         }else {
                             msgVo.setMsg("删除文件失败(mac地址:"+conf.getMacAddr()+")");
                             msgVo.setCode("error");
                         }
                    }catch (Exception e){
                        msgVo.setMsg("删除文件失败(mac地址:"+conf.getMacAddr()+")");
                        msgVo.setCode("error");
                        e.printStackTrace();
                    }

                }
            }
        }
        return msgVo;
    }

    /**
     * 修改dhcpd.conf
     * @param net
     * @return
     */
    @Override
    public boolean modifyLocalServce(Net net) {
        boolean bool =true;
        try {
            fileUtil.modifyLocalDHCP(net);
        }catch (Exception e){
            bool =false;
            e.printStackTrace();
        }

        return bool;
    }

    /***
     * 收集当前虚拟机信息
     * @return
     */
    @Override
    public Net gatherInfo() {
        Net net =fileUtil.gatherInfo();
        return net;
    }

}