package com.lwhtarena.pxe.util;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***************************************************************
 * 实现telnet远程服务器某个端口是否开通从而来判断远程服务器是否能连通
 ***************************************************************/
public class NetTelnetUtil {

    public static boolean checkServerAvaliable(String serverIp, String port, int timeout){
        boolean isConnect =false;
        TelnetClient telnet =new TelnetClient();
        telnet.setConnectTimeout(timeout);
        try {
            telnet.connect(serverIp,Integer.valueOf(port));
            if(StringUtil.isNotNull(telnet)){
                isConnect =telnet.isConnected();
            }
        } catch (IOException e) {
            //这里不需要打出堆栈 连不上就连不上
            System.out.println(e.getMessage());
        }finally {
            disconnect(telnet);
        }
        return isConnect;
    }

    public static void disconnect(TelnetClient telnet){
        System.out.println("Telnet disconnect.");
        try {
            telnet.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param ip 主机地址
     *
     * @return boolean
     * */
    public static boolean ping(String ip) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            String ipAddress = ip;
            int pingTimes = 2;
            int timeOut = 2000;
            BufferedReader in = null;
            Runtime r = Runtime.getRuntime();  // 将要执行的ping命令,此命令是windows格式的命令
            String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
            try {   // 执行命令并获取输出
                //System.out.println(pingCommand);
                Process p = r.exec(pingCommand);
                if (p == null) {
                    return false;
                }
                in = new BufferedReader(new InputStreamReader(p.getInputStream()));   // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
                int connectedCount = 0;
                String line = null;
                while ((line = in.readLine()) != null) {
                    connectedCount += getCheckResult(line);
                }   // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真
                return connectedCount == pingTimes;
            } catch (Exception ex) {
                ex.printStackTrace();   // 出现异常则返回假
                return false;
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            String host = ip;
            try {
                InetAddress address = null;
                if (host != null && host.trim().length() > 0) {
                    address = InetAddress.getByName(host);
                }
                if (address != null) {

                } else {
                    System.out.println(host + " is unrecongized");
                }
                if (address.isReachable(5000))
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }
        //若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
    private static int getCheckResult(String line) {  // System.out.println("控制台输出的结果为:"+line);
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)",    Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return 1;
        }
        return 0;
    }
}
