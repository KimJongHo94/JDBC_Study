/* ==============
	Test002.java
   ==============
*/

/*
실행 예)

번호 입력(-1 종료) : 11
이름 입력 : 조영욱
전화번호 입력 : 010-1234-1234
>> 데이터베이스 연결 성공~!!!
>> 회원 정보 입력 완료~!!!

번호 입력(-1 종료) : 12
이름 입력 : 조윤상
전화번호 입력 : 010-2345-2345
>> 데이터베이스 연결 성공~!!!
>> 회원 정보 입력 완료~!!!

번호 입력(-1 종료) : -1
>> 데이터에빙스 연결 닫힘~!!!
>> 프로그램 종료됨~!!!
*/

package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.util.DBConn;

public class Test002
{
	public static void main(String[] args)
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			Connection conn = DBConn.getConnection();
			
			do
			{
				System.out.print("번호 입력(-1 종료) : ");
				String sid = sc.next();
				
				if (sid.equals("-1"))
					break;
				
				System.out.print("이름 입력 : ");
				String name = sc.next();
				System.out.print("전화번호 입력 : ");
				String tel = sc.next();
				
				if (conn != null)
				{
					System.out.println("데이터베이스 연결 성공~!!!");
					
					try
					{
						
						// check~!!!
						// 작업 객체 생성 전에 쿼리문 준비~!!!
						String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
								+ "VALUES(?, ?, ?)";
						
						//-- check~!!!
						//   『?』IN 매개변수에 『'?'』 나 『"?"』 와 같이
						//   따옴표 붙이지 않음~!!!
						
						// check~!!!
						// 작업 객체 생성 과정에서 쿼리문(sql) 전달~!!!
						PreparedStatement pstmt = conn.prepareStatement(sql);
						
						// check~!!!
						// PreparedStatement 에 매개변수(?) 전달~!!!
						// 구성된 『?』의 순서에 따라 전달~!!!
						// Data Type 을 신경써서 전달~!!!
						pstmt.setInt(1, Integer.parseInt(sid));
						pstmt.setString(2, name);
						pstmt.setString(3, tel);
						
						// check~!!!
						// 작업 객체 실행 과정(execute)에서
						// 쿼리문 전달되지 않음~!!!
						int result = pstmt.executeUpdate();
						if (result >0)
							System.out.println("회원 정보 입력 완료~!!!");
						
						pstmt.close();
						
					} catch (Exception e)
					{
						System.out.println(e.toString());
					}
			
				}
				
				
			} while (true);
			
			// 반복문을 빠져나오게 되면 프로그램 종료
			DBConn.close();
			System.out.println(">> 데이터베이스 연결 닫힘~!!!");
			System.out.println(">> 프로그램 종료됨~!!!");
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
