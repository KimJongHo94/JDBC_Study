/* ==============
	Test001.java
   ==============
*/

package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		try
		{
			
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				// System.out.println("데이터베이스 연결 성공~!!!");
				
				try
				{
					/*
					Statement stmt = conn.createStatement();
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) "
							+ " VALUES(MEMBERSEQ.NEXTVAL, '허수민', '010-5555-5555')";
					
					int result = stmt.executeUpdate(sql);
					if (result > 0)
						System.out.println("데이터 입력 성공~!!!");
					stmt.close();
					*/
					
					
					/*
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) "
							+ " VALUES(MEMBERSEQ.NEXTVAL, '아무무', '010-7777-7777')";
					// Statement stmt = conn.createStatement();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					// int result = stmt.executeUpdate(sql);
					int result = pstmt.executeUpdate();
					if (result > 0)
						System.out.println("데이터 입력 성공~!!!");
					pstmt.close();
					*/
					
					
					/*
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) "
							+ " VALUES(MEMBERSEQ.NEXTVAL, ?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "볼베베");
					pstmt.setString(2, "010-8888-8888");
					
					int result = pstmt.executeUpdate();
					
					if (result > 0)
						System.out.println("데이터 입력 성공~!!!");
					pstmt.close();
					*/
					
					
					/*
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) "
							+ " VALUES(?, ?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 9);
					pstmt.setString(2, "나서스");
					pstmt.setString(3, "010-9999-9999");
					
					int result = pstmt.executeUpdate();
					
					if (result > 0)
						System.out.println("데이터 입력 성공~!!!");
					pstmt.close();
					*/
					
					
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) "
							+ " VALUES(?, ?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 10);
					pstmt.setString(2, "자르반");
					pstmt.setString(3, "010-9999-9999");
					
					// check~!!!
					System.out.println(sql);
					
					int result = pstmt.executeUpdate();
					
					if (result > 0)
						System.out.println("데이터 입력 성공~!!!");
					pstmt.close();
							
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
