//package com.lwhtarena.pxe.Thread;
//
//
//import com.lwhtarena.pxe.domain.Config;
//import com.lwhtarena.pxe.domain.InstallTask;
//import com.lwhtarena.pxe.util.NetTelnetUtil;
//import com.lwhtarena.pxe.util.Port;
//import com.lwhtarena.pxe.util.StringUtil;
//import com.sun.javaws.progress.Progress;
//
//import java.util.List;
//
//public class CheckRunnable implements Runnable{
//
//    InstallTask task;
//
//    public CheckRunnable(){}
//
//    public CheckRunnable(InstallTask task) {
//        this.task = task;
//    }
//
//    @Override
//    public void run() {
//        if(StringUtil.isNotNull(task)){
//            System.out.println("<<<<<<<<<<<<<<<<<<<< come in ");
//
//            try {
//                Thread.sleep(120*1000); //先休眠2分钟
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            List<Config> hosts = task.getHosts();
////            IpmiService ipmiService = new IpmiServiceIpml();
//
//            int num =hosts.size();
//            while (true && num>0 ){
//                if(hosts.size()>0){
//                    for(int i=0;i<hosts.size();i++){
//                        System.out.println("<<<<<<<<<<<<<<<<<<<<$$$$$$$$$$$$$$$"+ hosts.get(i).getHostIp());
//
//                        Progress process  =hosts.get(i).getProgress();
//                        /***************************
//                         * installState
//                         *   1:未完成
//                         *   2：winserver已完成
//                         *   3：已安装完成
//                         ***************************/
//                        if(process.getReBootSate() == 1){//重启中
//                            String ip =hosts.get(i).getHostIp();
//                            boolean bool_01 = NetTelnetUtil.checkServerAvaliable(ip, Port.WINSERVER_PORT,100*1000);
//                            boolean bool_02 = NetTelnetUtil.checkServerAvaliable(ip,Port.SYSTEM_PORT,60*1000);
//                            boolean bool_03 = NetTelnetUtil.checkServerAvaliable(ip, Port.WINSTORE_PORT,100*1000);
//
//                            if(bool_01 == true && bool_02 ==true){
//                                System.out.println("<<<<<<<<<<<<<<<<<<<<"+ 3+ bool_01);
//                                System.out.println("<<<<<<<<<<<<<<<<<<<<"+ 3+ bool_02);
//                                if(bool_03 == true){ //安装完成
//
//                                    System.out.println("<<<<<<<<<<<<<<<<<<<<&&&&&&  "+ hosts.get(i).getHostId());
//
//                                    hosts.get(i).getProgress().setInstallState(2); //winserver 和 winstore 安装完成
//                                    hosts.get(i).getProgress().setReBootSate(2); //重启完成
//
//                                    System.out.println("..>>>>>>>>>>>>>>>>>>>>>>>>>> "+hosts.get(i).getProgress().getInstallState()+" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//                                    System.out.println("#######################"+num);
//                                    num --;
//
//
//                                }else{
//                                    System.out.println(">>>>>> installed winserver("+hosts.get(i).getHostIp()+")  >>>>>>>");
//                                    hosts.get(i).getProgress().setInstallState(1); //安装完成winserver
////                                    try {
////                                        ipmiService.setOnByDisk(task.getTaskId(), hosts.get(i).getHostId());
////                                    } catch (Exception e) {
////                                        e.printStackTrace();
////                                    }
//                                    try {
//                                        Thread.sleep(10*1000); //休眠10秒
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//
//                            }else{
//                                System.out.println("<<<<<<<<<<<<<<<<<<<<xiumian");
//                                try {
//                                    Thread.sleep(10*1000); //休眠10秒
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//
//                    }
//                }
//            }
//            if(num<1){
//                task.setInStep(4);
//            }
//        }
//    }
//}
