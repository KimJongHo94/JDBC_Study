/* ===========================================
 	    MemberMain.java
 	    - main() 메소드를 포함하고 있는 클래스
   ===========================================
*/

/*
○ TBL_MEMBER 테이블을 활용하여
   이름과 전화번호를 여러 건 입력받고
   결과를 전체 출력하는 기능을 가진 프로그램을 구현한다.
   단, 데이터베이스 연동이 이루어져야 하고
   MemberDTO, MemberDAO 클래스를 활용해야 한다.
   
실행 예)

이름 전화번호 입력(1) : 김보경 010-1111-1111
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(2) : 이예슬 010-2222-2222
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(3) : 안혜리 010-3333-3333
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(4) : .

---------------------------------------------
전체 회원 수 : 3명 
---------------------------------------------
번호	이름	전화번호
 1	   김보경  010-1111-1111
 2	   이예슬  010-2222-2222
 3     안혜리  010-3333-3333
---------------------------------------------

- 프로그램 종료

*/

package com.test;

import java.util.Scanner;

import com.util.DBConn;

public class MemberMain
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			// MemberDAO 인스턴스 생성 
			MemberDAO dao = new MemberDAO();
			
			// 테스트
			// System.out.println("데이터베이스 연결 성공");
			
			int count = dao.count();		// 인원 수 확인 
			
			do
			{
				System.out.printf("이름 전화번호 입력(%d) : ", (++count));	// 1 2 3 ~~
				// 이름 전화번호 입력(1) : 김보경 010-1111-1111
				String name = sc.next();
				
				if(name.equals("."))
					break;
				
				String tel = sc.next();
				
				//--- 여기까지 수행하면 사용자로부터 이름과 전화번호를 입력받은 상태 
				
				//-- MemberDTO 생성 및 구성 
				MemberDTO dto = new MemberDTO();
				dto.setName(name);
				dto.setTel(tel);
				
				//-- dao 의 add() 메소드를 호출하기 위해서는 MemberDTO 가 필요한 상황 
				
				//-- TBL_MEMBER 테이블에 사용자가 넘겨준 데이터 입력 → dao 의 add() 메소드 호출 
				int result = dao.add(dto);
				if(result > 0) 
					System.out.println(">> 회원 정보 입력 완료~!!!");
				
			} while (true);
			
			/*
---------------------------------------------
전체 회원 수 : 3명 
---------------------------------------------
번호	이름	전화번호
 1	   김보경  010-1111-1111
 2	   이예슬  010-2222-2222
 3     안혜리  010-3333-3333
---------------------------------------------
			 */
			
			System.out.println();
			System.out.println("---------------------------------------------");
			System.out.printf("전체 회원 수 : %d명\n ", dao.count());
			System.out.println("---------------------------------------------");
			System.out.println("번호	이름	전화번호");
			
			
			for (MemberDTO obj : dao.lists())
			{
				System.out.printf("%3s %6s %12s\n", obj.getSid(), obj.getName(), obj.getTel());
			}
			
			System.out.println("---------------------------------------------");
			
			
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				DBConn.close();
				System.out.println("데이터베이스 연결 닫힘~!!!");
				System.out.println("프로그램 종료됨~!!!");
				
			} catch (Exception e2)
			{
				System.out.println(e2.toString());
			}
		}
	}
}
