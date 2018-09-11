package com.lwhtarena.pxe.service.ipmi;

import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.JSchException;
//import com.lwhtarena.pxe.Thread.CheckRunnable;
import com.lwhtarena.pxe.domain.Config;
import com.lwhtarena.pxe.domain.InstallTask;
import com.lwhtarena.pxe.domain.MsgVo;
import com.lwhtarena.pxe.service.HostService;
import com.lwhtarena.pxe.service.IpmiService;
import com.lwhtarena.pxe.util.ShellUtil;
import com.lwhtarena.pxe.util.ebs.Contex;
import com.lwhtarena.pxe.util.ebs.EBSrest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2017/9/18.
 */
@Service("hostService")
public class HostServiceIpml implements HostService {
    public static final Logger infoLog = LoggerFactory.getLogger(HostServiceIpml.class);
    @Autowired
    IpmiService ipmiService;
    @Autowired
    IpmiService hostService;
    public void startWinStoreStatus(final InstallTask task) {
        if(task.getWinStoreStatus() == null) {//防止多次进入时出现重复创建线程
            //定时更新配置进程
            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    List<Config> hosts = task.getHosts();
                    String url = "/api/v1/sds/system/progress/get";
                    boolean running = true;
                    while (running) {
                        running = false;
                        for (Config config : hosts) {
                            String ip = config.getHostIp();
                            JSONObject authJson = new JSONObject();
                            authJson.put("ipaddr", ip);
                            Contex contex = new Contex(new EBSrest());
                            String authStr = null;
                            try {
                                authStr = contex.put(ip, url, authJson.toString());
                                infoLog.info("progerss==>>  "+authStr);
                                org.json.JSONObject ebsAuthJson = new org.json.JSONObject(authStr);
                                int success = ebsAuthJson.getInt("success");
                                if (success == 1) {
                                    int progress = ebsAuthJson.getJSONObject("data").getInt("progress");
//                                    config.getProgress().setInitProgress(progress);
                                    if (progress < 100) {//只要有未完成的，线程就要继续跑
                                        running = true;
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                running = true;
                            }
                        }
                        if(running == false){
                            break;
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task.setWinStoreStatus(null);
                }
            };
            thread.start();//每隔3秒更新一下配置进度
            //定时更新配置进程
            task.setWinStoreStatus(thread);
        }
    }

    public Map<String,Integer> getWinStoreStatus(InstallTask task) {
//        this.startWinStoreStatus(task);
//        List<Config> hosts = task.getHosts();
//        int successHost = 0;
//        for (Config config:hosts) {
//            if(config.getProgress().getInitProgress() == 100){
//                successHost ++;
//            }
//        }
//        Map<String,Integer> dataHm = new HashMap<String, Integer>();
//        dataHm.put("success",successHost);
//        dataHm.put("total",hosts.size());
//        if(successHost == hosts.size()){
//            ShellUtil shellUtil = new ShellUtil();
//            String pubKeyPath = "~/.ssh/id_rsa";
//            String uName = "root";
//            String catCommand = "/tmp/sds_ins";//
//            boolean ins = true;
//            for (Config config:hosts) {
//                try {
//                    List <String> cStr = shellUtil.exceCmdRsa(uName, pubKeyPath, config.getHostIp(), 22, catCommand);
//                    if(cStr.get(0).equals("Success.") || cStr.get(0).equals("Storage system has been installed.")){
//                        config.getProgress().setWinStoreIns(1);
//                    }else{
//                        ins = false;
//                        config.getProgress().setWinStoreIns(0);
//                    }
//                } catch (JSchException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                };
//            }
//            if(ins == true) {
//                dataHm.put("state", 1);
//                task.setWinStoreStatus(null);
//                task.setInStep(5);
//            }else{
//                dataHm.put("state", 2);
//            }
//        }else{
//            dataHm.put("state",0);
//        }
//        return dataHm;
        return null;
    }

    public void initPoolHosts(final InstallTask task, final String hostId) {
//       new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                List<Config> hosts = task.getHosts();
//                if(hosts.get(0).getPoolName().trim().equals("")){
//                    String poolName = "pool"+new Date().getTime();
//                    for (Config config1:hosts){
//                        config1.setPoolName(poolName);
//                    }
//                }
//                ShellUtil shellUtil = new ShellUtil();
//
//                String pubKeyPath = "~/.ssh/id_rsa";
//                String uName = "root";
//
//                String createPoolCommand = "sh /tmp/Create_The_Pool.sh -C ";
//                String addPoolCommand = "sh /tmp/Create_The_Pool.sh -A ";
//                // 执行创建资源池脚本  开始
//                int size = hosts.size();
//                String poolName = hosts.get(0).getPoolName();
//                String ip = hosts.get(0).getHostIp();
//                String pwd = hosts.get(0).getHostPwd();
//                for (int i = 0; i < size; i++) {
//                    try {
//                        Config config = hosts.get(i);
//                        if (hostId == null || hostId.equals(config.getHostId())) {
//                            infoLog.info("安装或加入资源池中====>"+config.getHostId());
//                            if (i == 0) {
//                                List<String> strs = shellUtil.exceCmdRsa(uName, pubKeyPath, config.getHostIp(), 22, createPoolCommand + poolName);
//                                infoLog.info(config.getHostIp()+"加入资源池中===>"+createPoolCommand + poolName);
//                                for (String str:strs) {
//                                    infoLog.info("创建资源池结果===>"+str);
//                                }
//                                try {
//                                    Thread.sleep(1000*15);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            } else {
//                                List<String> strs = shellUtil.exceCmdRsa(uName, pubKeyPath, config.getHostIp(), 22, addPoolCommand + ip + " "+pwd);
//                                infoLog.info(config.getHostIp()+"加入资源池中===>"+addPoolCommand + ip + " "+pwd);
//                                for (String str:strs) {
//                                    infoLog.info("加入资源池结果===>"+str);
//                                }
//                                try {
//                                    Thread.sleep(1000*15);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    } catch (JSchException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
    }


    public void startPoolHostsStatus(final InstallTask task, final String hostId,final boolean hasInit) {
//        if(task != null  && task.getWinStoreStatus() == null) {//防止多次进入时出现重复创建线程
//            //定时更新配置进程
//            Thread thread = new Thread() {
//                @Override
//                public void run() {
//                    infoLog.info("启动资源池创建结果检测");
//                    super.run();
//                    if(hasInit == true) {
//                        initPoolHosts(task, hostId);//初始化
//                    }
//                    List<Config> hosts = task.getHosts();
//                    try {
//                        Thread.sleep(hosts.size()*15*1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    hosts = task.getHosts();
//                    ShellUtil shellUtil = new ShellUtil();
//                    String pubKeyPath = "~/.ssh/id_rsa";
//                    String uName = "root";
//                    boolean running = true;
//                    String poolCommand = "xe pool-list | grep name-label";
//                    Config config = hosts.get(0);
//
//                    //infoLog.info(poolCommand);
//                    //检测资源池创建情况  开始
//                    while (running){
//                        try {
//                            List <String> cStrs = shellUtil.exceCmdRsa(uName, pubKeyPath, config.getHostIp(), 22, poolCommand);
//                            infoLog.info("查看资源池是否存在命令=====》"+poolCommand);
//                            if(cStrs.size() > 0){
//
//                                String[] rs = cStrs.get(0).split(":");
//
//                                if(rs.length >= 2 && rs[1].trim().equals(config.getPoolName())){//资源池创建成功
//                                    infoLog.info("查看资源池是否存在结果=====》"+rs[1]);
//                                    break;
//                                }else{
//                                    infoLog.info("查看资源池是否存在结果===2==》"+cStrs);
//                                }
//                            }
//                            Thread.sleep(2000);
//                        } catch (JSchException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    //检测资源池创建情况  结束
//
//                    //检测所有主机是否已经加入了资源池  开始
//                    String hostCommand = "xe host-list | grep name-label";
//                    List<String> hostStates = new ArrayList<String>();
//                    while (true) {
//                        running = false;
//                        try {
//                            List <String> cStrs = shellUtil.exceCmdRsa(uName, pubKeyPath, config.getHostIp(), 22, hostCommand);
//                            infoLog.info("查看资源池中物物理机是否存在命令=====》"+poolCommand);
//                            if(cStrs.size() > 0){
////                                boolean isOver = true;
//                                for (String str:cStrs) {
//
//                                    String[] rs = str.split(":");
//                                    //infoLog.info(hostCommand+" >>>>>>>>>>>>>>>>>> "+str+"   >>>>>>>>>>>  "+rs[1].trim() +"          >>>>>>>>>>        "+config.getHostName());
//                                    if(rs.length >= 2 && rs[1] != null && !rs[1].trim().equals("")){//创建或加入成功
//                                       // infoLog.info(rs[1]);
//                                        hostStates.add(rs[1].trim());
//                                        infoLog.info("查看资源池中物物理机是否存在结果=====》物理机名字"+rs[1].trim());
//                                    }else{
////                                        running = false;
//                                        infoLog.info("查看资源池中物物理机是否存在结果===2==》"+str);
//                                    }
//                                }
////                                if(isOver == true){
////                                    break;
////                                }
//                            }
//                        } catch (JSchException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                       // infoLog.info(hostStates.size() +"++++++++++"+ hosts.size());
//
//                        if(hostStates.size() == hosts.size()){
//                            for (Config config2 : hosts) {
//                                config2.getProgress().setPoolInitState(2);
//                            }
//                            running = false;
//                        }else {
//                            for (Config config2 : hosts) {
//                                infoLog.info("配置的物理机名字=======》"+config2.getHostName());
//                                if (hostStates.contains(config2.getHostName())) {
//                                    config2.getProgress().setPoolInitState(2);
//                                } else {//只要还有未加入资源池的就要继续跑
//                                    running = true;
//                                }
//                            }
//                        }
//                        infoLog.info("running==============="+running);
//                        if(running == false){
//                            break;
//                        }
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    //检测所有主机是否已经加入了资源池  结束
//                    int total = hosts.size()*10000;
//                    task.setCountdownSecond(total);
//                    for (Config config1:hosts) {
//                        for (int i = 0 ; i < 10 ; i ++) {
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            total = total - 1;
//                            task.setCountdownSecond(total);
//                        }
//                    }
//                    //硬盘方式 重启
//                    try {
//
//                        //先置为未重启状态
//
//                        //启动状态监控
//                        if(hosts != null && hosts.size() > 0) {
//                            for (Config config2 : hosts) {
//                                shellUtil.exceCmdRsa(uName, pubKeyPath, config2.getHostIp(), 22, "chkconfig diamond off");
//                                ipmiService.setOnByDisk(task.getTaskId(),config2.getHostId());
//                                shellUtil.exceCmdRsa(uName, pubKeyPath, config2.getHostIp(), 22, "reboot");
//                                config2.getProgress().setReBootSate(1);
//                            }
//                        }
//                        Thread.sleep(120*1000);
//                        CheckRunnable checkRunnable = new CheckRunnable(task);
//                        new Thread(checkRunnable).start();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    //重启
//                }
//            };
//            task.setWinStoreStatus(thread);
//            thread.start();//每隔3秒更新一下配置进度
//            //定时更新配置进程
//
//        }else{
//            infoLog.info("eeeewwwwww");
//        }
    }

    @Override
    public void modifyToByDisk(final String taskId) {
//       new Thread(){
//           @Override
//           public void run() {
//               try {
//                   Thread.sleep(7*60*1000);
//                   try {
//                       InstallManager installManager =InstallManager.getInstance();
//                       InstallTask task =installManager.getTaskById(taskId);
//                       IpmiService ipmiService = new IpmiServiceIpml();
//                       List<Config> hosts = task.getHosts();
//                       for(Config conf:hosts){
//                           ipmiService.setOnByDisk(task.getTaskId(), conf.getHostId());
////                           Thread.sleep(2000);
////                           ipmiService.setOnByDisk(task.getTaskId(), conf.getHostId());
////                           Thread.sleep(2000);
////                           ipmiService.setOnByDisk(task.getTaskId(), conf.getHostId());
////                           Thread.sleep(2000);
////                           ipmiService.setOnByDisk(task.getTaskId(), conf.getHostId());
//                           infoLog.info(">>>>>>>>>>>>>>>>>>77777777 "+conf.getHostIp());
//                       }
//
//                   } catch (Exception e) {
//                       e.printStackTrace();
//                   }
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }
//           }
//       }.start();

    }

    //    public org.json.JSONArray getPoolHostsStatus(InstallTask task) {
//        List<Config> hosts = task.getHosts();
//        //org.json.JSONObject poolJson = new org.json.JSONObject();
//        org.json.JSONArray hostsJson = new org.json.JSONArray();
//        for (Config config:hosts){
//            org.json.JSONObject hostJson = new org.json.JSONObject();
//            hostJson.put("name",config.getHostName());
//            hostJson.put("ip",config.getHostIp());
//            hostJson.put("id",config.getHostId());
//            Progress progress = config.getProgress();
//            int state = 1;
//            String msg = "待创建/加入资源池";
//            if(progress.getPoolInitState() == 2){
//                state = 2;
//                msg = "资源池创建/加入完成";
//            }
//            int rebootState = progress.getReBootSate();
//            if(rebootState == 1){
//                state = 3;
//                msg = "重启中";
//            }else if(rebootState == 2){
//                state = 3;
//                msg = "重启完成";
//            }
//            hostJson.put("state",state);
//            hostJson.put("msg",msg);
//            hostsJson.put(hostJson);
//        }
//       // poolJson.put("data",hostsJson);
//        return hostsJson;
//    }


    /**
     * 重新初始化（关闭winstore进程）
     * @param task
     * @param msgVo
     */
    @Override
    public void reInit(InstallTask task, MsgVo msgVo) {
        List<Config> configs = task.getHosts();
        List<String> error = new ArrayList<String>();
        ShellUtil shellUtil = new ShellUtil();

        String pubKeyPath = "~/.ssh/id_rsa";
        String uName ="root";
        String command ="/opt/sdsom/tools/rescue/sandstone_reset.sh --yes-i-am-sure";
        for(Config conf:configs){
            try {
                List<String> cStr =shellUtil.exceCmdRsa(uName,pubKeyPath,conf.getHostIp(),22,command);
                try {
                    Thread.sleep(1000*10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    ipmiService.reBootByDisk(task.getTaskId(),conf.getHostId());//执行完成后需要重启才能解决
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (JSchException e) {
                msgVo.setCode("error");
                error.add("主机（"+conf.getHostIp()+"）执行/opt/sdsom/tools/rescue/sandstone_reset.sh --yes-i-am-sure 失败");
                e.printStackTrace();
            } catch (IOException e) {
                msgVo.setCode("error");
                error.add("主机（"+conf.getHostIp()+"）执行/opt/sdsom/tools/rescue/sandstone_reset.sh --yes-i-am-sure 失败");
                e.printStackTrace();
            }
        }
        //task.setWinStoreStatus(null);
        msgVo.getDetail().put("error",error);
        if(!(error.size()>0)){
            msgVo.setMsg("初始化成功！");
            msgVo.setCode("success");
        }
    }
}
