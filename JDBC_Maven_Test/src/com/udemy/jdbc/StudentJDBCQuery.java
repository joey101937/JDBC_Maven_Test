package com.udemy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class StudentJDBCQuery {
	private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private String DATABASE_URL = "jdbc:mysql://localhost/udemy?serverTimezone=UTC";
	private String USER = "root";
	private String PASSWORD = null;
	private ResultSet resultSet = null; 
	
	public void setPassword(String p){
		PASSWORD = p;
	}
	
	public void readDatabase(){
		Connection connection = null;
		Statement statement = null;
		String p = JOptionPane.showInputDialog("Password for " + USER);
		setPassword(p);
		try{
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
			statement = connection.createStatement();
			String command = "SELECT * FROM Students";
			resultSet = statement.executeQuery(command);
			
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				System.out.println(id +" " + name + " " + age);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
