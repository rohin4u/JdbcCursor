package com;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String driver = "oracle.jdbc.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "system";
		String pwd = "password";
		Class.forName(driver);
		
		Connection con = DriverManager.getConnection(jdbc_url, user, pwd);
		
		CallableStatement statement = con.prepareCall("{call getAllEmpInfo(?)}");
		
		statement.registerOutParameter(1,OracleTypes.CURSOR );
		
		statement.execute();
		
		ResultSet rset = (ResultSet) statement.getObject(1);
		
		while(rset.next()) {
			
			System.out.println(rset.getInt(1)+","+rset.getString(2)
			+","+rset.getInt(3)+", "+rset.getString(4));
		}
		con.close();
		
		
		
		

	}

}
