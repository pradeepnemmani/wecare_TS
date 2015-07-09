package com.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
public static Connection getConnection()throws Exception{
	Class.forName("com.mysql.jdbc.Driver");
	return DriverManager.getConnection("jdbc:mysql://localhost:3306/wecare","root","TIGER");
}
}
