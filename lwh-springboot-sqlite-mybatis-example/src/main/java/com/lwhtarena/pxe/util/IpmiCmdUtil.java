package com.lwhtarena.pxe.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by yqy on 2017/9/18.
 */
public class IpmiCmdUtil {

    public static final String IPMI_LANPLUS =    "lanplus";
    public static final String IPMI_LAN =    "lan";

    public static final String IPMI_BOOT_PEX =    "chassis bootdev pxe";
    public static final String IPMI_POWER_OFF =    "chassis  power off";
    public static final String IPMI_POWER_ON =    "chassis  power on";
    public static final String IPMI_POWER_STATUS =    "chassis power status";
    public static final String IPMI_BOOT_DISK  =    "chassis bootdev disk";
    public static final String IPMI_SENSOR  =    " sdr ";

    public static final String source = "ipmitool -I {0} -H {1} -U {2} -P {3} ";

    private static boolean  isLinux;
    private static boolean  isWindows;

    public static final String windows = " cmd /c \"D: && cd /pro/ipmitool  &&  ";
    public static final String windowsEnd = "\"";

    public static final String linux = "/bin/sh -c ";

    // 创建线程池
    static ExecutorService exec = Executors.newCachedThreadPool();
    static {
        String osName = System.getProperty("os.name");
        if(osName != null){
            osName = osName.toLowerCase();
        }
        if (osName.indexOf("windows") >= 0) {
            isWindows = true;
            isLinux = false;
        } else {
            isWindows = false;
            isLinux = true;
        }
    }

    public static void executeCmd(String cmd,List<String> list) throws InterruptedException, ExecutionException, TimeoutException {
        if(list == null){
            return ;
        }
        Process process = executeCmd(cmd);
        InputStream stderr = process.getErrorStream();
        InputStream stdin = process.getInputStream();
        StreamGobbler outputGobbler =  new StreamGobbler(stdin, "Output");
        StreamGobbler outputGobblerErr =  new StreamGobbler(stderr, "ERROR");
        FutureTask<List<String>> resultFutureTask = new FutureTask<List<String>>(outputGobbler);
        exec.submit(resultFutureTask);
        exec.submit(outputGobblerErr);
        list.addAll(resultFutureTask.get(10, TimeUnit.SECONDS)) ;
    }

    public static void executeCmd(String cmd,Map<String,Object> map){
        if(map == null){
            return ;
        }
        Process proc = null;
        try {
//			LogUtil.ipmiInfoLog.info("executeCmd isLinux:"+isLinux+";isWindows:"+isWindows);
            if (isWindows) {
                cmd = windows + cmd + windowsEnd;
                proc = Runtime.getRuntime().exec(cmd);
            }
            if (isLinux) {
                String[] cmds = {"/bin/sh", "-c", cmd};
                proc = Runtime.getRuntime().exec(cmds);
            }
        } catch (IOException e1) {

        }
        String line = null;
        List<String> list = new ArrayList<String>();
        StringBuffer buffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader
                    (new InputStreamReader(proc.getInputStream(), "gbk"));
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("string",buffer.toString());
        map.put("list",list);
    }

    public static Process executeCmd(String cmd){
        Process proc = null;
        try {
//			LogUtil.ipmiInfoLog.info("executeCmd isLinux:"+isLinux+";isWindows:"+isWindows);
            if (isWindows) {
                cmd = windows + cmd + windowsEnd;
                proc = Runtime.getRuntime().exec(cmd);
            }
            if (isLinux) {
                String[] cmds = {"/bin/sh", "-c", cmd};
                proc = Runtime.getRuntime().exec(cmds);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return proc;
    }

    public static String executeCmdReString(String cmd){
        Process proc = null;
        try {
//			LogUtil.ipmiInfoLog.info("executeCmd isLinux:"+isLinux+";isWindows:"+isWindows);
            if (isWindows) {
                cmd = windows + cmd + windowsEnd;
                proc = Runtime.getRuntime().exec(cmd);
            }
            if (isLinux) {
                String[] cmds = {"/bin/sh", "-c", cmd};
                proc = Runtime.getRuntime().exec(cmds);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String line = null;
        List<String> list = new ArrayList<String>();
        StringBuffer buffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader
                    (new InputStreamReader(proc.getInputStream(), "gbk"));
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    static class StreamGobbler implements Callable<List<String>> {

        InputStream is;
        String type;

        public StreamGobbler(InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        @Override
        public List<String> call() throws Exception {
            List<String> list = new ArrayList<String>();
            try {
                InputStreamReader isr = new InputStreamReader(is,"gbk");
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null) {
                    list.add(line);
                    if (type.equals("Error")) {
                        System.out.println("Error   :" + line);
                    } else {
                        System.out.println("Debug:" + line);
                    }
                    if(line.indexOf("Error")>=0){//报错跳出循环
                        System.out.println("StreamGobbler 报错跳出循环");
                        break;
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }finally {
                if(is!=null){
                    is.close();
                }
            }
            return list;
        }
    }

}
