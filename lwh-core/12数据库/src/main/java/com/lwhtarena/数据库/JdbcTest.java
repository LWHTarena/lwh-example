package com.lwhtarena.数据库;

import java.sql.*;

/**
 * @version v1.0
 * @author liwenhao
 *
 */
public class JdbcTest {
	public static void main(String[] args) {


		//1.注册驱动
		//2.通过驱动管理器获得链接
		//格式：jdbc:oracle:thin:@<IP地址>:<端口号，默认1521>：<sid>
		//3.编写sql语句
		//4.获取statement对象
		//5.传递对象
		//6.关闭资源
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:orcl",
					"scott",
					"tiger");
			Statement st =conn.createStatement();
			String sql ="select dname,loc from dept where deptno =10";
			ResultSet rs =st.executeQuery(sql);
			while(rs.next()){
				String dname =rs.getString("dname");
				String loc =rs.getString("loc");
				System.out.println(dname+ ":"+ loc);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
