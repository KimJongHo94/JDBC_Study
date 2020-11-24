package com.test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	private Connection conn;
	
	// 데이터베이스 연결 메소드
	public ScoreDAO() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
	}
	
	// 데이터 입력 -> insert -> stmt.executeUpdate 문 사용
	public int add(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = "INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) "
				     + " VALUES(SCORESEQ.NEXTVAL, '%s', %d, %d, %d )";
					//               sid         namd  kor  eng mat
		result = stmt.executeUpdate(sql);
		
		// 닫아주기
		stmt.close();
		
		return result;
	}
	
	// 전체 리스트 출력 메소드
	// ArrayList 사용 <ScoreDTO> 
	public ArrayList<ScoreDTO> lists(String name) throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT)/3 AS AVG FROM TBL_SCORE ORDER BY SID";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			
			result.add(dto);
		}
		
		return result;
	}
	
	// 데이터베이스 연결 종료
	public void close() throws SQLException
	{
		if (conn != null) 
		{
			DBConn.close();
		}
	}
}
