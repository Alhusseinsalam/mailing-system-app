package dev.husein.util;

import java.sql.*;

public class ConnectionProvider {
	public static Connection get(){
	Connection con=null;
	try{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:1234:test","admin","admin");
	}catch(Exception e){System.out.println(e);}
	return con;
    }
}
