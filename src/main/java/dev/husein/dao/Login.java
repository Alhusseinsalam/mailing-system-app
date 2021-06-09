package dev.husein.dao;

import java.sql.*;

import dev.husein.util.ConnectionProvider;

public class Login {

	public static boolean validate(String email,String password){
		boolean status=false;
		try{
			Connection con= ConnectionProvider.get();
			PreparedStatement ps=con.prepareStatement("select * from company_mailer_user where email=? and password=? and authorized=?");
			ps.setString(1,email);
			ps.setString(2,password);
			ps.setString(3,"yes");
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				status=true;
			}
			
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
}
