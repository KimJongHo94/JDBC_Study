/* ================
	ScoreMain.java
   ================
*/
// 원래는 ScoreDAO, ScoreDTO 부터 먼저 작업하는 것이 옳다.

/*
○ 성적 처리 → 데이터베이스 연동(데이터베이스 연결 및 액션 처리)
			    ScoreDTO 클래스 활용(속성만 존재하는 클래스. 사용자 정의 자료형. getter / setter 구성)
			    ScoreDAO 클래스 활용(데이터베이스 액션 처리 전용 클래스 구성)
			    Process  클래스 활용(단위 기능 구성)
			    
//    여러 명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
//    총점, 평균을 연산하여 출력하는 프로그램을 구현한다.
//    출력 시 번호(이름, 총점 등) 오름차순 정렬하여 출력한다.

	  ※ 서브 메뉴 구성 → Process 클래스 활용

실행 예) 

===[성적처리]===
=================
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 입력
4. 성적 수정
5. 성적 삭제 
=================
>> 선택(1~5, -1종료) : 1

4번 학생 성적 입력(이름 국어 영어 수학) : 강정우 50 60 70
5번 학생 성적 입력(이름 국어 영어 수학) : 권소윤 80 80 80
6번 학생 성적 입력(이름 국어 영어 수학) : .					→ 메인 메뉴 돌아감

=================
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 입력
4. 성적 수정
5. 성적 삭제 
=================
>> 선택(1~5, -1종료) : 2

전체 인원 : 5명
번호	이름	국어	영어	수학	총점	평균	석차
 1     
 2
 3							...
 4
 5
 
 =================
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 입력
4. 성적 수정
5. 성적 삭제 
=================
>> 선택(1~5, -1종료) : -1

프로그램 종료되었습니다.

*/

package com.test;

import java.util.Scanner;

public class ScoreMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Process obj = new Process();
		
		do
		{
			
			System.out.println();
			System.out.println("===[성적처리]===       ");
			System.out.println("=================  ");
			System.out.println("1. 성적 입력           ");
			System.out.println("2. 성적 전체 출력        ");
			System.out.println("3. 이름 검색 입력        ");
			System.out.println("4. 성적 수정           ");
			System.out.println("5. 성적 삭제           ");
			System.out.println("=================  ");
			System.out.print(">> 선택(1~5, -1종료) : ");
			
			String menus = sc.next();
			
			try
			{
				int menu = Integer.parseInt(menus);
				
				if(menu == -1)
				{
					System.out.println();System.out.println(">> 프로그램이 종료되었습니다.");
					return;
				}
				
				switch (menu)
				{
					case 1:
						// 성적 입력 기능 수행
						obj.sungjukInsert();
						break;
					case 2:
						// 성적 전체 출력 기능 수행
						obj.sungjukSelectAll();
						break;
					case 3:
						// 이름 검색 기능 수행
						obj.sungjukSearchName();
						break;
					case 4:
						// 성적 수정 기능 수행
						obj.sungjukUpdate();
						break;
					case 5:
						// 성적 삭제 기능 수행
						obj.sungjukDelete();
						break;
				default:
					break;
				}
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		} while (true);
	}
}
