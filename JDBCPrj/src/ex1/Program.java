package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		
		Connection con;
		
		try
		{	
			String url = "jdbc:oracle:thin:@211.238.142.165:1521:xe";
			String user = "scott";
			String pwd = "tiger";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pwd);
			
			String sql = "SELECT * FROM NOTICE";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			/*
			if (rs.next())
			{
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				System.out.println(title);
				//--==>> 결과 집합을 모두 소모했음(결과값 존재 x)
				//--==>> java.sql.SQLException: 결과 집합을 모두 소모했음
			}
			
			*/
			while (rs.next())
			{
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regDate = rs.getDate("REGDATE");
				String content = rs.getString("CONTENT");
				int hit = rs.getInt("HIT");
				
				System.out.printf("id:%d, title:%s, writerId:%s, regDate:%s, content:%s, hit:%d\n"
							      , id, title, writerId, regDate, content, hit);

			}
			
			// 자원 닫기
			rs.close();
			st.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}		
		
	}
}
