/* ======================================
 	    Test003.java
 	    - select 쿼리문 전송 및 결과 수신
   ======================================
*/


package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.util.DBConn;

public class Test004
{
	public static void main(String[] args)
	{
		// 데이터베이스 연결 객체 생성
		Connection conn = DBConn.getConnection();
		
		if (conn != null)
		{
			System.out.println(">> 데이터베이스 연결 성공~!!!");
		
			try
			{
				// 작업 객체 생성
				Statement stmt = conn.createStatement();
				
				// 쿼리문 준비
				String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY 1";
				
				// 쿼리문 수행
				ResultSet rs = stmt.executeQuery(sql);
				
				// executeQuery() 메소드를 사용하면
				// 질의 결과를 ResultSet 객체로 가져올 수 있다.
				// 하지만, ResultSet 객체가 질의에 대한 결과물 모두를
				// 한꺼번에 받아서 가지고 있는 구조가 아니다.
				// 단지, 데이터베이스로부터 획득한 질의 결과물에 대한
				// 관리가 가능한 상태가 되는 것이다.
				// 이 때문에... ResultSet 을 수신했다고 해서
				// 데이터베이스와의 연결을 끊게 되면
				// ResultSet 객체는 더 이상 질의 결과를 관리할 수 없게 된다.
				
				// ResultSet 의 다음 값의 존재 여부 확인(반환) → true / false
				// 반복문을 활용한 ResultSet 컨트롤
				while (rs.next())
				{
					String sid = rs.getString("SID");			// String sid = rs.setString(1)
					String name = rs.getString("NAME");			// String name = rs.setString(2)
					String tel = rs.getString("TEL");			// String tel = rs.setString(3)
					
					String str = String.format("%3s %8s %12s", sid, name, tel);
					
					System.out.println(str);
				}
				
				rs.close();		// 결과집합 리소스 반납
				stmt.close();   // 작업객체 리소스 반납 
				
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}// end if
		
		DBConn.close();		// 연결객체 리소스 반납 
		
		System.out.println(">> 데이터베이스 연결 닫힘~!!!");
		System.out.println(">> 프로그램 종료됨~!!!");
	}
}
/*
>> 데이터베이스 연결 성공~!!!
  1      정의진 010-1111-1111
  2      박다빈 010-2222-2222
  3      김승범 010-3333-3333
  4      안혜리 010-4444-4444
>> 데이터베이스 연결 닫힘~!!!
>> 프로그램 종료됨~!!!
*/

	

