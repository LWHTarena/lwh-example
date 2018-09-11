package com.lwhtarena.pxe.service.ipmi;

import com.lwhtarena.pxe.domain.Config;
import com.lwhtarena.pxe.domain.InstallTask;
import com.lwhtarena.pxe.domain.IpmiBean;
import com.lwhtarena.pxe.service.IpmiService;
import com.lwhtarena.pxe.util.IpmiCmdUtil;
import com.lwhtarena.pxe.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service("ipmiService")
public class IpmiServiceIpml implements IpmiService {
    public static final Logger infoLog = LoggerFactory.getLogger(IpmiServiceIpml.class);



    @Override
    public boolean testConnection(IpmiBean ipmiBean) throws Exception {
        List<String> reList = new ArrayList<String>();
        String cmd = getIpmiSource(ipmiBean.getIpmiIP(),ipmiBean.getUsername(),ipmiBean.getPwd());
        IpmiCmdUtil.executeCmd(cmd+IpmiCmdUtil.IPMI_SENSOR,reList);

        if(reList.size()<2){
            return false;
        }
        return true;
    }

    public String exec(IpmiBean ipmiBean, String cmd) throws Exception {
        //具体都命令
//        IpmiCmdUtil.executeCmd();
        String link = getIpmiSource(ipmiBean.getIpmiIP(),ipmiBean.getUsername(),ipmiBean.getPwd());
        return IpmiCmdUtil.executeCmdReString(link+cmd);
    }

    @Override
    public boolean execOnByPxe(String taskId, String hostId) throws Exception {
//        Config config = getIpmiBean(taskId,hostId);
//        String ipmiSource = "ipmitool -I lnaplus -H ";//8 10  3000 1000 350  600 150   20w    2w    10w   5    6w    2000    30     3套   10次
//        // 的脸
        String cmd = getIpmiSource(taskId,hostId);
        IpmiCmdUtil.executeCmd(cmd+IpmiCmdUtil.IPMI_BOOT_PEX);//设置下次从PXE启动
        infoLog.info("execOnByPxe "+ cmd+IpmiCmdUtil.IPMI_BOOT_PEX);
        Thread.sleep(5000);
        IpmiCmdUtil.executeCmd(cmd+IpmiCmdUtil.IPMI_POWER_ON);
        infoLog.info("execOnByPxe "+ cmd+IpmiCmdUtil.IPMI_POWER_ON);
        return true;
    }
    public boolean execOnByPxe(IpmiBean ipmiBean) throws Exception {
//        Config config = getIpmiBean(taskId,hostId);
//        String ipmiSource = "ipmitool -I lnaplus -H ";//8 10  3000 1000 350  600 150   20w    2w    10w   5    6w    2000    30     3套   10次
//        //
        String cmd = getIpmiSource(ipmiBean.getIpmiIP(),ipmiBean.getUsername(),ipmiBean.getPwd());
        IpmiCmdUtil.executeCmd(cmd+IpmiCmdUtil.IPMI_BOOT_PEX);//设置下次从PXE启动
        infoLog.info("execOnByPxe "+ cmd+IpmiCmdUtil.IPMI_BOOT_PEX);
        Thread.sleep(5000);
        IpmiCmdUtil.executeCmd(cmd+IpmiCmdUtil.IPMI_POWER_ON);
        infoLog.info("execOnByPxe "+ cmd+IpmiCmdUtil.IPMI_POWER_ON);
        return true;
    }

    @Override
    public boolean execOnByDisk(String taskId, String hostId) throws Exception {

        String cmd = getIpmiSource(taskId,hostId);
        IpmiCmdUtil.executeCmd(cmd+IpmiCmdUtil.IPMI_BOOT_DISK);//设置下次从PXE启动
        infoLog.info("execOnByDisk "+ cmd+IpmiCmdUtil.IPMI_BOOT_DISK);
        Thread.sleep(5000);
        IpmiCmdUtil.executeCmd(cmd+IpmiCmdUtil.IPMI_POWER_ON);
        infoLog.info("execOnByDisk "+ cmd+IpmiCmdUtil.IPMI_POWER_ON);
        return true;
    }

    @Override
    public boolean setOnByDisk(String taskId, String hostId) throws Exception {
        String cmd = getIpmiSource(taskId,hostId);
        IpmiCmdUtil.executeCmd(cmd+IpmiCmdUtil.IPMI_BOOT_DISK);//设置下次从PXE启动
        infoLog.info("setOnByDisk "+ cmd+IpmiCmdUtil.IPMI_BOOT_DISK);
        return true;
    }

    public boolean execOff(String taskId,String hostId) throws Exception {
        String cmd = getIpmiSource(taskId,hostId);
        IpmiCmdUtil.executeCmd(cmd+IpmiCmdUtil.IPMI_POWER_OFF);
        infoLog.info("execOff "+ cmd+IpmiCmdUtil.IPMI_POWER_OFF);
        return true;
    }

    @Override
    public boolean execListOff(String taskId) throws Exception {
//        InstallManager installManager = InstallManager.getInstance();
//        InstallTask task = installManager.getTaskById(taskId);
//        List<Config> configs =  task.getHosts();
//        for(Config config: configs){
//            execOff(taskId,config.getHostId());
//        }
        return true;
    }

    public boolean reBootByPxe(final String taskId, final String hostId) throws Exception {
        //重启需要异步轮询状态启动
        if(StringUtil.isNull(hostId)){
            return false;
        }
        try{
            String  re = getPowerState(taskId,hostId);
            String cmd = getIpmiSource(taskId,hostId);
            if(re!=null&&re.indexOf("on")>-1){
                //先关机
                //异步 轮询\后启动
                IpmiCmdUtil.executeCmdReString(cmd+IpmiCmdUtil.IPMI_POWER_OFF);//设置下次从PXE启动
                infoLog.info("reBootByPxe "+ cmd+IpmiCmdUtil.IPMI_POWER_OFF);
                final Thread th  = new Thread(){
                    @Override
                    public void run() {
                        boolean inOff = true;
                         while (inOff){
                             try {
                                 String  re = getPowerState(taskId,hostId);
                                 if(re!=null&&re.indexOf("off")>-1){
                                     inOff =false;
                                 }
                             } catch (Exception e) {
                                 e.printStackTrace();
                             }
                         }
                        try {
                            execOnByPxe(taskId, hostId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                th.start();
            }else if(re.indexOf("off")>-1){
                //启动
                execOnByPxe(taskId,hostId);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean reBootByPxe(final IpmiBean ipmiBean) throws Exception {
        if(ipmiBean==null){
            return false;
        }
        try{
            String  re = getPowerState(ipmiBean);
            String cmd = getIpmiSource(ipmiBean.getIpmiIP(),ipmiBean.getUsername(),ipmiBean.getPwd());
            if(re!=null&&re.indexOf("on")>-1){
                //先关机
                //异步 轮询\后启动    比方说访问外网 请求阿里云接口    DNS劫持
                IpmiCmdUtil.executeCmdReString(cmd+IpmiCmdUtil.IPMI_POWER_OFF);//关机
                infoLog.info("reBootByPxe "+ cmd+IpmiCmdUtil.IPMI_POWER_OFF);
                final Thread th  = new Thread(){
                    @Override
                    public void run() {
                        boolean inOff = true;
                        while (inOff){
                            try {
                                String  re = getPowerState(ipmiBean);
                                if(re!=null&&re.indexOf("off")>-1){
                                    inOff =false;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            execOnByPxe(ipmiBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                th.start();
            }else if(re.indexOf("off")>-1){
                //启动
                execOnByPxe(ipmiBean);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean reBootByDisk(final String taskId, final String hostId) throws Exception {
        //重启需要异步轮询状态启动
        if(StringUtil.isNull(hostId)){
            return false;
        }
        try{
            String  re = getPowerState(taskId,hostId);
            String cmd = getIpmiSource(taskId,hostId);
            if(re!=null&&re.indexOf("on")>-1){
                //先关机
                //异步 轮询\后启动
                IpmiCmdUtil.executeCmdReString(cmd+IpmiCmdUtil.IPMI_POWER_OFF);//关机
                infoLog.info("reBootByDisk "+ cmd+IpmiCmdUtil.IPMI_POWER_OFF);
                final Thread th  = new Thread(){
                    @Override
                    public void run() {
                        boolean inOff = true;
                        while (inOff){
                            try {
                                String  re = getPowerState(taskId,hostId);
                                if(re!=null&&re.indexOf("off")>-1){
                                    inOff =false;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            execOnByDisk(taskId, hostId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                th.start();
            }else if(re.indexOf("off")>-1){
                //启动
                execOnByDisk(taskId,hostId);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean reBootListByPxe(String taskId) throws Exception {
//        InstallManager installManager = InstallManager.getInstance();
//        InstallTask task = installManager.getTaskById(taskId);
//        List<Config> configs =  task.getHosts();
//        for(Config config: configs){
//            reBootByPxe(taskId,config.getHostId());
//        }
        return true;
    }

    @Override
    public boolean reBootListByDisk(String taskId) throws Exception {
//        InstallManager installManager = InstallManager.getInstance();
//        InstallTask task = installManager.getTaskById(taskId);
//        List<Config> configs =  task.getHosts();
//        for(Config config: configs){
//            reBootByDisk(taskId,config.getHostId());
//        }
        return true;
    }

    public String getPowerState(String taskId,String hostId) throws Exception {
        String cmd = getIpmiSource(taskId,hostId);
        return  IpmiCmdUtil.executeCmdReString(cmd+IpmiCmdUtil.IPMI_POWER_STATUS);
    }

    public String getPowerState(IpmiBean ipmiBean) throws Exception {
        String cmd = getIpmiSource(ipmiBean.getIpmiIP(),ipmiBean.getUsername(),ipmiBean.getPwd());
        return  IpmiCmdUtil.executeCmdReString(cmd+IpmiCmdUtil.IPMI_POWER_STATUS);
    }

    private String getIpmiSource(String taskId,String hostId){
//        InstallManager installManager = InstallManager.getInstance();
//        installManager.getTaskById(taskId);
//        Config config  = installManager.getConfigByTaskIdHostId(taskId,hostId);
//        return MessageFormat.format(IpmiCmdUtil.source, IpmiCmdUtil.IPMI_LANPLUS, config.getIpmiIp(), config.getIpmiUname(),config.getIpmiPwd());
        return null;
    }

    private String getIpmiSource(String ip,String uname,String pwd){
        return MessageFormat.format(IpmiCmdUtil.source, IpmiCmdUtil.IPMI_LANPLUS, ip, uname,pwd);
    }




}
