package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
   private Connection conn;
   
   public Connection connection()
    {
         conn = DBConn.getConnection();
         return conn;
   }
   public int add(MemberDTO dto) throws SQLException
      {
         int result = 0;
         
         // 작업객체 생성
         Statement stmt = conn.createStatement();
         
         // 쿼리문 준비
         String sql = String.format("INSERT INTO TBL_EMP (EMP_ID, EMP_NAME, SSN"
               + ", IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG) " + 
               " VALUES (EMPSEQ.NEXTVAL, '%s', '%s', TO_DATE('%s', 'YYYY-MM-DD'), %d, '%s', %d, %d, %d, %d)"
               , dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity_id(), dto.getTel(), dto.getBuseo_id(), dto.getJikwi_id(), dto.getBasicpay(), dto.getSudang());
         result = stmt.executeUpdate(sql);
         
         // 닫기
         stmt.close();
         
         // 결과값 반환
         return result;
      }
   
   // getData
   public ArrayList<String> getData(String type) throws SQLException
   	{
   		ArrayList<String> result = new ArrayList<String>();
   		
   		Statement stmt = conn.createStatement();
   		
   		String sql = "";
   		
   		switch(type)
   		{
   		case "city": sql = "SELECT CITY_LOC FROM TBL_CITY"; break;
   		case "buseo": sql = "SELECT BUSEO_NAME FROM TBL_BUSEO"; break;
   		case "jikwi": sql = "SELECT JIKWI_NAME FROM TBL_JIKWI"; break;
   		}
   		
   		ResultSet rs = stmt.executeQuery(sql);
   		
   		while(rs.next())
   		{
   			result.add(rs.getString(1));
   		}
   		
   		rs.close();
   		stmt.close();
   		
   		return result;
   	}
      
      public int count() throws SQLException
      {
         int result = 0;
         
         Statement stmt = conn.createStatement();
         String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
         
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next())
         {
            result = rs.getInt("COUNT");
         }
         
         rs.close();
         stmt.close();
         
         return result;
      }
      
      public ArrayList<MemberDTO> lists(int i) throws SQLException // 전체출력 * 5
      {
         ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
         String orderby = "";
         
         switch (i)
         {
         case 1: orderby = "EMP_ID";         
            break;
         case 2: orderby = "EMP_NAME";
            break;
         case 3: orderby = "BUSEO_NAME";
            break;
         case 4: orderby = "JIKWI_NAME";
            break;
         case 5: orderby = "PAY DESC";
         }
         
         Statement stmt = conn.createStatement();
         String sql = String.format("SELECT * FROM EMPVIEW ORDER BY %s", orderby);
         
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next())
         {
            MemberDTO dto = new MemberDTO();
            

            dto.setEmp_id(rs.getInt("EMP_ID"));
            dto.setName(rs.getString("EMP_NAME"));
            dto.setSsn(rs.getString("SSN"));
            dto.setIbsadate(rs.getString("IBSADATE"));
            dto.setCity(rs.getString("CITY_LOC"));
            dto.setTel(rs.getString("TEL"));
            dto.setBuseo(rs.getString("BUSEO_NAME"));
            dto.setJikwi(rs.getString("JIKWI_NAME"));
            dto.setMin_basicpay(rs.getInt("MIN_BASICPAY"));
            dto.setBasicpay(rs.getInt("BASICPAY"));
            dto.setSudang(rs.getInt("SUDANG"));
            dto.setPay(rs.getInt("PAY"));
            
            result.add(dto);
            
         }
         return result;
      }
      
      
         // 검색 출력
            public ArrayList<MemberDTO> searchLists(int index, String search) throws SQLException
            {
               ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
               
               Statement stmt = conn.createStatement();
               
               String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE"
                        + ", CITY_LOC, TEL, BUSEO_NAME, JIKWI_NAME "
                        + ", MIN_BASICPAY, BASICPAY, SUDANG, PAY"
                        + " FROM EMPVIEW";
               
               switch(index)
               {
               case 1: sql += String.format(" WHERE EMP_ID = %s", search); break;
               case 2: sql += String.format(" WHERE EMP_NAME LIKE '%%%s%%'", search); break;
               case 3: sql += String.format(" WHERE BUSEO_NAME = '%s'", search); break;
               case 4: sql += String.format(" WHERE JIKWI_NAME = '%s'", search); break;
               }
               
               ResultSet rs = stmt.executeQuery(sql);
               
               while(rs.next())
               {
                  MemberDTO obj = new MemberDTO();
                  
                  obj.setEmp_id(rs.getInt("EMP_ID"));
                  obj.setName(rs.getString("EMP_NAME"));
                  obj.setSsn(rs.getString("SSN"));
                  obj.setIbsadate(rs.getString("IBSADATE"));
                  obj.setCity(rs.getString("CITY_LOC"));
                  obj.setTel(rs.getString("TEL"));
                  obj.setBuseo(rs.getString("BUSEO_NAME"));
                  obj.setJikwi(rs.getString("JIKWI_NAME"));
                  obj.setMin_basicpay(rs.getInt("MIN_BASICPAY"));
                  obj.setBasicpay(rs.getInt("BASICPAY"));
                  obj.setSudang(rs.getInt("SUDANG"));
                  obj.setPay(rs.getInt("PAY"));
                  
                  result.add(obj);
               }
               
               rs.close();
               stmt.close();
               
               return result;
            }
          
      
            
      // 삭제 메소드
      public int remove(int emp_id) throws SQLException   // 삭제
      {
         int result = 0;
         
         //작업객체 생성
         Statement stmt = conn.createStatement();
         
         //쿼리문 준비
         String sql = String.format("DELETE "
                           +" FROM TBL_EMP "
                           + " WHERE EMP_ID = %d", emp_id);
               
         //쿼리문 실행
         result = stmt.executeUpdate(sql);
               
         //작업객체 종료
         stmt.close();
               
         //결과값 반환
         return result;
         
      }
      
      public int minbasicpay(String jikwi) throws SQLException
      {
         int result = 0;
         
         Statement stmt = conn.createStatement();
         String sql = String.format("SELECT J.MIN_BASICPAY AS MIN_BASICPAY FROM TBL_EMP E , TBL_JIKWI J WHERE JIKWI_NAME = '%s'", jikwi);
         
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next())
         {
            result = rs.getInt("MIN_BASICPAY");
         }
         
         rs.close();
         stmt.close();
         
         return result;
      }
      
      // 직원 사원 번호 검색
      public ArrayList<MemberDTO> lists1(int emp_id) throws SQLException
      {
         ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
         
         Statement stmt = conn.createStatement();
         String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE"
               + ", CITY_LOC, TEL, BUSEO_NAME, JIKWI_NAME"
               + ", BASICPAY, SUDANG, PAY"
               + " FROM EMPVIEW"
               + " WHERE EMP_ID = %d", emp_id);
         
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next())
         {
            MemberDTO dto = new MemberDTO();
            dto.setEmp_id(rs.getInt("EMP_ID"));
            dto.setName(rs.getString("EMP_NAME"));
            dto.setSsn(rs.getString("SSN"));
            dto.setIbsadate(rs.getString("IBSADATE"));
            dto.setCity(rs.getString("CITY_LOC"));
            dto.setTel(rs.getString("TEL"));
            dto.setBuseo(rs.getString("BUSEO_NAME"));
            dto.setJikwi(rs.getString("JIKWI_NAME"));
            dto.setBasicpay(rs.getInt("BASICPAY"));
            dto.setSudang(rs.getInt("SUDANG"));
            dto.setPay(rs.getInt("PAY"));
            
            result.add(dto);
            
         }
         
         rs.close();
         stmt.close();
         
         return result;
      }
          
          
      // 직원 정보 수정
     public int update(MemberDTO dto) throws SQLException
     {
        int result = 0;
        
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE TBL_EMP SET EMP_NAME = '%s', SSN = '%s'"
              + ", IBSADATE = TO_DATE('%s', 'YYYY-MM-DD'), CITY_ID = %d"
              + ", TEL = '%s', BUSEO_ID = %d, JIKWI_ID = %d"
              + ", BASICPAY = %d, SUDANG = %d"
              + " WHERE EMP_ID = %d"
              , dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity_id(), dto.getTel()
              , dto.getBuseo_id(), dto.getJikwi_id(), dto.getBasicpay(), dto.getSudang(), dto.getEmp_id());
        
        result = stmt.executeUpdate(sql);
        
        return result;
     }
       
      
      
      public int SearchCityID(String name) throws SQLException
      {
         int result = 0;
         
         Statement stmt = conn.createStatement();
         String sql = String.format("SELECT CITY_ID FROM TBL_CITY WHERE CITY_LOC = '%s'", name);
         
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next())
         {
            result = rs.getInt("CITY_ID");
         }
         
         rs.close();
         stmt.close();
         
         return result;
      }
      public int SearchBuseoID(String name) throws SQLException
      {
         int result = 0;
         
         Statement stmt = conn.createStatement();
         String sql = String.format("SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s'", name);
         
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next())
         {
            result = rs.getInt("BUSEO_ID");
         }
         
         rs.close();
         stmt.close();
         
         return result;
      }
      public int SearchJikwiID(String name) throws SQLException
      {
         int result = 0;
         
         Statement stmt = conn.createStatement();
         String sql = String.format("SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'", name);
         
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next())
         {
            result = rs.getInt("JIKWI_ID");
         }
         
         rs.close();
         stmt.close();
         
         return result;
      }
      
      public void close()
      {
         if (conn != null)
         {
            DBConn.close();
         }
      }
      
      
}