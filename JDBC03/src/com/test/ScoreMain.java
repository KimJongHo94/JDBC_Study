/* ===================
	  ScoreMain.java
   ===================
*/

// ○ 실습 문제
//    성적 처리 프로그램을 구현한다. → 데이터베이스 연동 → ScoreDAO, ScoreDTO

//    여러 명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
//    총점, 평균을 연산하여 출력하는 프로그램을 구현한다.
//    출력 시 번호(이름, 총점 등) 오름차순 정렬하여 출력한다.

// 실행 예)
/*
1번 학생 성적 입력(이름 국어 영어 수학) : 안혜지 80 75 60
2번 학생 성적 입력(이름 국어 영어 수학) : 허수민 100 90 80
3번 학생 성적 입력(이름 국어 영어 수학) : 박해진 80 85 80
4번 학생 성적 입력(이름 국어 영어 수학) : .

-------------------------------------------------------
번호	이름	국어	영어	수학	총점	평균
-------------------------------------------------------
  1		안혜지	 80      75      60      
  2     허수민  100      90      80    
  3     박해진   80      85      80
-------------------------------------------------------   
*/


package com.test;

import java.util.Scanner;

public class ScoreMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int count = 0;
		
		try
		{
			
			ScoreDAO dao = new ScoreDAO();
			
			// 연결 성공
			// System.out.println("데이터베이스 연결 성공~!!!");
			
			do
			{
				
				System.out.print("%d번 학생 성적 입력(이름 국어 영어 수학) :");
				
				String name = sc.next();
				
				// 입력할 때 . 을 입력하면 입력 끝
				if (name.equals("."))
					break;
				
			} while (true);
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
