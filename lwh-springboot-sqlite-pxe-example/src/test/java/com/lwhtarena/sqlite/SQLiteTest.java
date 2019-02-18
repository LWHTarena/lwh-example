package com.lwhtarena.sqlite;

import java.sql.*;

public class SQLiteTest {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection("jdbc:sqlite:lwh.db");
            Statement statement =connection.createStatement();
            ResultSet resultSet =statement.executeQuery("SELECT * FROM tbl1");

            while (resultSet.next()){
                String name =resultSet.getString("name");
                String salary =resultSet.getString("salary");
                System.out.println(name +"月薪是："+salary);
            }
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
