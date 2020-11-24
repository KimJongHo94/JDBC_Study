package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	// 주요 속성 구성
	private Connection conn;
	
	public Connection connection() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
		
		return conn;
	}
	
	// 인원 수 확인 담당 메소드
	public int count() 
	{
		int result = 0;
		
		
		try
		{
			String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				result = rs.getInt("COUNT");
			rs.close();
			pstmt.close();

			
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		}

		return result;
	}
	
	
	// 데이터 입력 메소드
	public int add(ScoreDTO dto) throws SQLException 
	{
		int result = 0;
		
		String sql = "INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) "
				+ " VALUES(SCORESEQ.NEXTVAL, ?, ?, ?, ?)";
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// set
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getKor());
			pstmt.setInt(3, dto.getEng());
			pstmt.setInt(3, dto.getMat());
			
			
			
			result = pstmt.executeUpdate();
			if (result > 0)
				System.out.println(">> 데이터 입력 성공~!!!");
			
			pstmt.close();
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return result;
				
	}
	
	// 전체 리스트 출력 담당 메소드
	public ArrayList<ScoreDTO> lists() 
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		String sql = "SELECT SID, NAME, KOR, ENG, MAT" + ", (KOR+ENG+MAT) AS TOT" + ", (KOR+ENG+MAT)/3 AS AVG"
				+ ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK" + " FROM TBL_SCORE" + " ORDER BY SID ASC";
		
	
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
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
				dto.setRank(rs.getInt("RANK"));
	
				result.add(dto);
			}
			rs.close();
			pstmt.close();
			
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		}
		return result;
	}
	
	
	// 이름 검색 담당 메소드
	public ArrayList<ScoreDTO> lists(String name)
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		String sql = String.format("SELECT *" + " FROM (SELECT SID, NAME, KOR, ENG, MAT" + ", (KOR+ENG+MAT) AS TOT"
				+ ", (KOR+ENG+MAT)/3 AS AVG" + ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK" + " FROM TBL_SCORE)"
				+ " WHERE NAME=?", name);
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			
			ResultSet rs = pstmt.executeQuery();
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
				dto.setRank(rs.getInt("RANK"));

				result.add(dto);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		}
		return result;
	}
	
	
	// 데이터 수정 담당 메소드
	public int modify(ScoreDTO dto) 
	{
		int result = 0;
		
		try
		{
			String sql = String.format("UPDATE TBL_SCORE" + " SET NAME=?, KOR=?, ENG=?, MAT=? WHERE SID=?",
					dto.getName(), dto.getKor(), dto.getEng(), dto.getMat(), dto.getSid());
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getKor());
			pstmt.setInt(3,  dto.getEng());
			pstmt.setInt(4, dto.getMat());
			pstmt.setString(5, dto.getSid());
			
			result = pstmt.executeUpdate();
			pstmt.close();

		} catch (SQLException e)
		{
			System.out.println(e.toString());
		}
		return result;
	}

	// 데이터 삭제 담당 메소드
	public int remove(int sid) 
	{
		int result = 0;
		
		String sql = String.format("DELETE FROM TBL_SCORE WHERE SID=?", sid);
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,sid);
			
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		}
		return result;
	}

	// 데이터베이스 연결 종료 담당 메소드
	public void close() 
	{
		try
		{
			DBConn.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
}
