package design_pattern.prototype.scene01.advance;

import java.util.Random;
/**
 * 原型模式适合什么场合？一是类初始化需要非常多的资源，这个资源包括数据。引荐资源的等
 * ；而是通过new产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式；三是
 * 一个对象需要提供给其他对象访问，而且各个调用则可能都需要修改其值时，可以考虑使用原型
 * 模式拷贝多个对象供调用者使用。
 * 在实际项目中，原型模式很少单独使用，一般是和工厂方法模式一起出现，通过clone的方法创建
 * 一个对象，然后由工厂方法提供调用者。
 * @author liwenhao
 *
 */
public class Client {

	//发送账单的数量，这个值是从数据中获得
	private static int MAX_COUNT =6;

	public static void main(String[] args) {
		//模拟发送邮件
		int i =0;

		//把模板定义出来，这个是从数据库中获得
		Mail mail =new Mail(new AdvTemplate());
		mail.setTail("xxx银行版权所有");
		while(i<MAX_COUNT){
			//以下是每封邮件不同的地方
			Mail cloneMail =mail.clone();
			mail.setAppellation(getRandString(5)+"先生（女士）");
			mail.setReceiver(getRandString(5)+"@"+getRandString(8)+".com");

			//然后发送邮件
			sendMail(mail);
			i++;
		}


	}

	private static void sendMail(Mail mail) {
		System.out.println("标题："+mail.getSubject()+"\t收件人："+mail.getReceiver()+"\t...发送成功！");

	}

	//获得指定长度的随机字符串
	private static String getRandString(int maxLength) {
		String source ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb =new StringBuffer();
		Random rand =new Random();
		for(int i=0;i<maxLength;i++){
			sb.append(source.charAt(rand.nextInt(source.length())));
		}

		return sb.toString();
	}

}
