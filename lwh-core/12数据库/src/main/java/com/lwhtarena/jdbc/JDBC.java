package com.lwhtarena.jdbc;

import java.sql.*;

/**
 * 用JDBC查询学生成绩单，把主要代码写出来（考试概率极大）--mysql演示
 * @author lwh
 * @folder com.lwhtarena.jdbc
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class JDBC {
    public static void main(String[] args) {

        Connection conn =null;
        PreparedStatement ps =null;
        ResultSet rs =null;

        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //连接数据库
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/库名?useUnicode=true&characterEncoding=utf8"
                    ,"root","tarena");
            //编写sql语句
            String sql =".....";
            //发送sql语句
            ps =conn.prepareStatement(sql);
            //发送sql语句
            //返回结果集
            //关闭连接
            rs =ps.executeQuery();
            while(rs.next()){
                System.out.println("...");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("注册驱动失败");
        } catch (SQLException e) {
            throw new RuntimeException("连接数据库失败");
        }finally{
            if(rs!=null)
                try{
                    rs.close();
                }catch(Exception e){

                }
            if(ps!=null)
                try{
                    ps.close();
                }catch(Exception e){

                }
            if(conn!=null)
                try{
                    conn.close();
                }catch(Exception e){

                }
        }

    }
}
