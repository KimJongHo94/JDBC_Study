/* ================
	ScoreMain.java
   ================
*/

/*
○ 실습 문제

   성적 처리 → 데이터베이스 연동(데이터베이스 연결 및 액션 처리)
                ScoreDTO 활용(속성만 존재하는 클래스, getter / setter 구성)
                ScoreDAO 활용(데이터베이스 액션 처리 전용 클래스 활용)
                ScoreProcess 활용(단위 기능 구성)
                ※ 단, PreparedStatement 활용	// check~!!!
   
   여러 명의 이름, 국어점수, 영어점수, 수학점수 입력받아
   총점, 평균, 석차 등을 연산하여 출력하는 프로그램을 구현한다.
   
   ※ 서브 메뉴 구성 → ScoreProcess 활용		// check~!!!
   
실행 예)

===[성적 처리]===
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=================
>> 선택(1~5, -1종료) : 
*/

package com.test;

import java.util.Scanner;

public class ScoreMain
{
	public static void main(String[] args)
	{
		ScoreProcess prc = new ScoreProcess();
		Scanner sc = new Scanner(System.in);
		
		do
		{
			System.out.println();
			System.out.println("====[ 성적 처리 ]====");
			System.out.println("1. 성적 입력");
			System.out.println("2. 성적 전체 출력");
			System.out.println("3. 이름 검색 출력");
			System.out.println("4. 성적 수정");
			System.out.println("5. 성적 삭제");
			System.out.println("=====================");
			System.out.print(">> 선택(1~5, -1종료) :");
			String menus = sc.next();
			
			try
			{
				int menu = Integer.parseInt(menus);
				
				if (menu == -1)
				{
					System.out.println();
					System.out.println("프로그램이 종료되었습니다.");
					return;
				}
				
				switch (menu)
				{
					case 1:
						// 성적 입력 기능 수행
						prc.sungjukInsert();
						break;
					case 2:
						// 성적 전체 출력 기능 수행
						prc.sungjukSelctAll();
						break;
					case 3:
						// 이름 검색 출력 기능 수행
						prc.sungjukSearchName();
						break;
					case 4:
						// 성적 수정 기능 수행
						prc.sungjukUpdate();
						break;
					case 5:
						// 성적 삭제 기능 수행
						prc.sungjukDelete();
						break;
				}
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		} while (true);
		
	
	}
}

