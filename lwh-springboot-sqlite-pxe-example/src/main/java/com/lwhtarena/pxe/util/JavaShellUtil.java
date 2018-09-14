package com.lwhtarena.pxe.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol>调用shell脚本</ol>
 * <h2>功能描述：调用shell脚本，操作虚拟机</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class JavaShellUtil {

    public static final Logger infoLog = LoggerFactory.getLogger(JavaShellUtil.class);

    public boolean executeShell(String cmdStr) throws IOException {
        boolean succFlag = false;
        GobblerThread outputGobbler = null;
        GobblerThread errorGobbler = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            infoLog.info(df.format(new Date()) + " 准备执行Shell命令[" + cmdStr + "]");
            Process pid = null;
            String[] cmd = {"/bin/sh", "-c", cmdStr};
            pid = Runtime.getRuntime().exec(cmd);
            if (pid != null) {
                infoLog.info("进程号[" + pid.toString() + "]");
                System.out.println("进程号[" + pid.toString() + "]");

                // 获取进程的标准输入流
                outputGobbler = new GobblerThread(pid.getInputStream(), "STD_OUT");
                // 获取进程的错误流
                errorGobbler = new GobblerThread(pid.getErrorStream(), "STD_ERR");
                // 启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
                outputGobbler.start();
                errorGobbler.start();

                // 等待脚本执行完毕，获取执行返回值
                int exitCode = pid.waitFor();
                infoLog.info(cmdStr + ":::返回值::" + exitCode);
                if (exitCode == 0) {
                    succFlag = true;
                }
            } else {
                infoLog.info("没有启动Shell进程");
            }

            infoLog.info(df.format(new Date()) + " Shell命令执行完毕");
        } catch (Exception e) {
            infoLog.info("执行Shell命令时发生异常:" + e);
            return succFlag;
        }

        return succFlag;
    }

    /**
     * 获取shell返回的值
     *
     * @throws IOException
     */
    public static String getReturnFormShell(String cmdStr) throws IOException {
        String[] cmds = {"/bin/sh", "-c", cmdStr};

        // 执行shell脚本
        Process pcs = Runtime.getRuntime().exec(cmds);

        // 定义shell返回值
        StringBuffer result = new StringBuffer();

        // 获取shell返回流
        BufferedInputStream in = new BufferedInputStream(pcs.getInputStream());
        // 字符流转换字节流
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        // 这里也可以输出文本日志

        String lineStr;
        while ((lineStr = br.readLine()) != null) {
            result.append(lineStr + ";");
        }
        // 关闭输入流
        br.close();
        in.close();

        return result.toString();
    }

    /**
     * 组装Shell参数
     *
     * @param args
     * @return
     */
    public static String getShellParams(String... args) {
        StringBuffer sb = new StringBuffer(1024);
        for (String arg : args) {
            sb.append(" " + "'" + arg + "'");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        //shell脚本接收的参数
        String params = JavaShellUtil.getShellParams("参数1", "参数2");

        JavaShellUtil javaShellUtil = new JavaShellUtil();
        try {
            javaShellUtil.executeShell("sh /root/shell/startTomcat.sh " + params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class GobblerThread extends Thread {
    InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    // 输出流的类型ERROR或OUTPUT
    String type;

    GobblerThread(InputStream is, String type) {
        this.is = is;
        this.type = type;
    }

    @Override
    public void run() {
        try {
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(type + ">" + line);
                System.out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {

            }
        }
    }
}