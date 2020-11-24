/* ==================================================
	Test002.java
	- CallableStatement 를 활용한 SQL 구문 전송 실습2
   ==================================================
*/

package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

public class Test002
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn!=null)
			{
				System.out.println("데이터베이스 연결 성공~!!!");
				
				try
				{
					String sql = "{call PRC_MEMBERSELECT(?)}";
					CallableStatement cstmt = conn.prepareCall(sql);
					
					
					
					// 1. Project Explorer 에서 프로젝트명 우클릭
					//    > Build Path > Configure Build Path... 클릭
					//    → 대화창 오픈
					// 2. 열린 대화창의 Library 탭 클릭
					//    > 우측의 Add External jar 버튼 클릭
					//    > 『ojdbc6.jar』 파일 추가 등록 
					//    (외부 jar 파일 연결)
					// 3. import 구문을 통해
					//    『oracle.jdbc.OracleTypes;』 추가
					
					// ---- 걷네주고 실행시키기만
					cstmt.registerOutParameter(1, OracleTypes.CURSOR);
					cstmt.execute();
					// -------------------------
					
					// Object obj = cstmt.getObject(1);
					ResultSet rs = (ResultSet)cstmt.getObject(1);	// 객체를 얻어오게 됨
					// 다운캐스팅
					
					while (rs.next())
					{
						String sid = rs.getString("SID");
						String name = rs.getString("NAME");
						String tel = rs.getString("TEL");
						
						String str = String.format("%3s %7s %10s", sid, name, tel);
						
						System.out.println(str);
					}
					rs.close();
					cstmt.close();
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}// end if
			
			DBConn.close();
			System.out.println("\n데이터베이스 연결 닫힘~!!!!");
			System.out.println("프로그램 종료됨~!!!");

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}

/*
데이터베이스 연결 성공~!!!
  1     김보경 010-1111-1111
  2     이예슬 010-2222-2222
  3     안혜리 010-3333-3333
  4     안혜지 010-4444-4444
  5     허수민 010-5555-5555
  6     진영은 010-6666-6666
  7     아무무 010-7777-7777
  8     볼베베 010-8888-8888
  9     나서스 010-9999-9999
 10     자르반 010-9999-9999
 11     조영욱 010-1234-1234
 12     조윤상 010-2345-2345
 13     김종호 010-4321-4321
 14     김일웅 010-1414-1414
 15     강정우 010-1515-1515
 16     조인경 010-1313-1313

데이터베이스 연결 닫힘~!!!!
프로그램 종료됨~!!!
*/