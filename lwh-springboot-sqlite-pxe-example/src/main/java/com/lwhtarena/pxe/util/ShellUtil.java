package com.lwhtarena.pxe.util;


import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ShellUtil {

	/**
	 *
	 * @param userName 登录账号
	 * @param pubKeyPath  秘钥
	 * @param host   主机ip
	 * @param port   端口号
	 * @param cmd    执行命令行
	 * @return
	 * @throws JSchException
	 * @throws IOException
	 */
	public List<String> exceCmdRsa(String userName,String pubKeyPath,String host,Integer port,String cmd) throws JSchException, IOException{
		JSch jsch = new JSch(); // 创建JSch对象
		if(port == null||port<=0){
			port = 22;
		}
		jsch.addIdentity(pubKeyPath);
		Session session = jsch.getSession(userName, host, port); // 根据用户名，主机ip，端口获取一个Session对象
		//session.setPassword(password); // 设置密码
		session.setUserInfo(new MyUserInfo());
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config); // 为Session对象设置properties
		int timeout = 5000;
		session.setTimeout(timeout); // 设置timeout时间
		session.connect(); // 通过Session建立链接
		ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
		channelExec.setCommand(cmd);
//		channelExec.setErrStream(System.err);
		channelExec.connect();
		InputStream in = channelExec.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
		String buf = null;
		List<String> li = new ArrayList<String>();
		while ((buf = reader.readLine()) != null) {
			li.add(buf);
//			System.out.println(buf);// 打印控制台输出
		}
		reader.close();
		channelExec.disconnect();
		if (null != session) {
			session.disconnect();
		}
		return li;
	}

	private static class MyUserInfo implements UserInfo {
		public String getPassphrase() {
//			LogUtil.ipmiSystemLog.info("getPassphrase");
			return null;
		}
		public String getPassword() {
//			LogUtil.ipmiSystemLog.info("getPassword");
			return null;
		}
		public boolean promptPassword(String s) {
//			LogUtil.ipmiSystemLog.info("promptPassword"+s);
			return false;
		}
		public boolean promptPassphrase(String s) {
//			LogUtil.ipmiSystemLog.info("promptPassphrase:"+s);
			return false;
		}
		public boolean promptYesNo(String s) {
//			LogUtil.ipmiSystemLog.info("promptYesNo:"+s);
			return true;//notice here!
		}
		public void showMessage(String s) {
//			LogUtil.ipmiSystemLog.info("showMessage:"+s);
		}
	}


}
