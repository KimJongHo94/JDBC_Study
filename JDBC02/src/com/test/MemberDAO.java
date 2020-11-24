/* ============================================
 	    MemberDAO.java
 	    - 데이터베이스 액션 처리 전용 객체 구성 
   ============================================
*/

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// 주요 변수 선언 → DB 연결 객체  
	private Connection conn;
	
	// 생성자 정의 → 사용자 정의 생성자 
	public MemberDAO() throws ClassNotFoundException, SQLException
	{
		// DB 연결 
		conn = DBConn.getConnection();
	}
	
	// 기능 → 메소드 정의 → 데이터를 입력하는 기능 → insert 쿼리문 수행
	public int add(MemberDTO dto) throws SQLException
	{
		// 결과값으로 반환하게 될 변수 선언 및 초기화
		// (적용된 행의 갯수)
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		//	createStatement() : 데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 만든다.
		
		// ※ Statement 의 종류
		//    - Statement
		//      하나의 쿼리를 사용하고나면 더 이상 사용할 수 없다.
		//    - PreparedStatement
		//      하나의 PreparedStatement 로 쿼리를 여러 번 처리할 수 있다.
		//    - CallableStatement
		//      데이터베이스 내의 스토어드 프로시저나 함수 등을 호출할 수 있다.
		
		// ※ Statement 의 의미
		//    자바에서 사용되는 3가지 종료의 작업 객체들은
		//    데이터베이스로 쿼리를 담아서 보내는 그릇 정도로 생각하자.
		//    즉, 작업 객체에 쿼리를 실어 데이터베이스로 보내버리면
		//    그 내용이 데이터베이스 내에서 처리되는 것이다.
		//    이 때, 한 번 사용하고 버리는 그릇은 Statement 이며,
		//    재사용이 가능한 그릇은 PreparedStatement 이다.
		
		// 쿼리문 준비
		String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL)" 
								   + " VALUES(MEMBERSEQ.NEXTVAL, '%s', '%s')", dto.getName(), dto.getTel());
		
		// 쿼리문 실행
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
		
	}//end add()
	
	// 기능 → 메소드 정의 → 인원 수를 파악하는 기능 → select 쿼리문 수행
	public int count() throws SQLException
	{
		// 결과값으로 반환하게 될 변수 선언 및 초기화
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBER";
		
		// 쿼리문 실행 → select 쿼리문 → ResultSet 반환
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 반복문 구성 
		while (rs.next())	// if (rs.next()) → 단일행을 받게 했기 때문에 
		{
			result = rs.getInt("COUNT");		// result = rs.getInt(1);
		}
		
		rs.close();
		stmt.close();
		
		return result;
		
	}//end count()
	
	
	
	// 기능 → 메소드 정의 → 데이터 전체 조회하는 기능 → select 쿼리문 수행
	public ArrayList<MemberDTO> lists() throws SQLException
	{
		// 결과값으로 반환하게 될 변수 선언 및 초기화
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			// MemberDTO 인스턴스 생성 
			MemberDTO dto = new MemberDTO();
			
			// 생성된 MemberDTO 객체에 속성들 채워넣기 
			dto.setSid(rs.getString("SID"));		// dto.setSid(rs.getString(1));
			dto.setName(rs.getString("NAME"));		// dto.setName(rs.getString(2));
			dto.setTel(rs.getString("TEL"));		// dto.setTel(rs.getString(3));
													// ※ 컬럼의 인덱스는 0이 아니라 1부터...
			
			result.add(dto);
			
		}
		
		rs.close();
		stmt.close();
		
		// 결과값 반환
		return result;
	}
	
	/*
	 ※ 반환 자료형에 대한 고찰
	 SELECT NAME
	 FROM TBL_MEMBER
	 WHERE SID = 1;
	 → String
	 
	 SELECT NAME
	 FROM TBL_MEMBER;
	 → String들 → String 배열이나... String 을 요소로 취하는 자료구조 
	 
	 SELECT SID, NAME, TEL
	 FROM TBL_MEMBER
	 WHERE SID = 1;
	 → MemberDTO
	 
	 SELECT SID, NAME, TEL
	 FROM TBL_MEMBER
	 → MemberDTO들 → MemberDTO 배열이나... MemberDTO 를 요소로 취하는 자료구조 
	 */
	
	// (완료)
	// 기능 → 메소드 정의 → 데이터베이스 연결 종료
	public void close() throws SQLException
	{
		DBConn.close();
	}
	
}// end MemberDAO
