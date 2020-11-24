/* ===============
	DBConn.java
   ===============
*/


package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn
{
	private static Connection dbConn;
	
	public static Connection getConnection()
	{
		
		if (dbConn == null)
		{
			try
			{
				String url = "jdbc:oracle:thin:@211.238.142.165:1521:xe";
				String user = "scott";
				String pwd = "tiger";
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		return dbConn;
		
		
	}
	
	
	public static Connection getConnection(String url, String user, String pwd)
	{
		// check~!!!
		if (dbConn == null)
		{
			try
			{

				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
				// 매개변수를 받아서 위에 변수로 대체해서 진행한다.
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		// check~!!!
		return dbConn;
		
		
	}
	
	public static void close()
	{
		if (dbConn != null)
		{
			try
			{
				if (!dbConn.isClosed())
				{
					dbConn.close();
				}
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		// check~!!!
		dbConn = null;
	}
	
}
