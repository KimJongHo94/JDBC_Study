/* =============
	Process.java
   =============
*/

package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class Process
{
	// 주요 속성 구성 → 데이터베이스 액션 처리 전담 객체 → ScoreDAO
	private ScoreDAO dao;
	private Scanner sc;
	
	// 생성자 정의 → 사용자 정의 생성자
	public Process()
	{
		dao = new ScoreDAO();
	}
	
	// 주요 기능 구성
	// - 성적 입력
	public void sungjukInsert()
	{
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			int count = dao.count();
			sc = new Scanner(System.in);
			
			do
			{
				
				System.out.println();
				System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", (++count));
				String name = sc.next();
				
				if(name.equals("."))
					break;
				
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				ScoreDTO dto = new ScoreDTO(); // add 호출
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				
				int result = dao.add(dto);
				if(result > 0)
					System.out.println(">> 성적 입력이 완료되었습니다.");
				
			} while (true);
			
			dao.close();
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	
	// - 성적 전체 출력
	public void sungjukSelectAll()
	{
		/*
		try
		{
			
			// 데이터베이스 연결	
			dao.connection();
			
			int count = dao.count();
			
			System.out.printf("전체 인원 : %d명\n", count);
			System.out.println("번호  이름  국어  영어  수학  총점  평균  석차");
			
			for (ScoreDTO dto : dao.lists())
			{
				System.out.printf("  %s  %s  %d  %d  %d  %d  %2f  %d\n"
						         , dto.getSid()
						         , dto.getName()
						         , dto.getKor()
						         , dto.getEng()
						         , dto.getMat()
						         , dto.getTot()
						         , dto.getAvg()
						         , dto.getRank() );
						       
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		*/
		
		// 선생님 코드
		
		try
		{
			
			// 데이터베이스 연결
			dao.connection();
			
			
			// 전체 인원 수 체크
			int count = dao.count();
			
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", count);
			System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
			
			for (ScoreDTO dto : dao.lists())
			{
				System.out.printf("%3s %4s %5d %4d %4d %5d %5.1f %5d\n"
								 , dto.getSid()
								 , dto.getName()
								 , dto.getKor()
								 , dto.getEng()
								 , dto.getMat()
								 , dto.getTot()
								 , dto.getAvg()
								 , dto.getRank());
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	// - 이름 검색 출력
	public void sungjukSearchName()
	{
		/*
		try
		{
			
			// 데이터베이스 연결 
			dao.connection();
			
			sc = new Scanner(System.in);
			
			do
			{
				
				System.out.print("검색할 이름 입력 : ");
				String name = sc.next();
				
				// 이름 입력 칸에 . 을 입력하면 현재(do-while) 반복문 벗어나기
				if (name.equals("."))
					break;
				
				ArrayList<ScoreDTO> ar = dao.lists(name);
				
				System.out.println("번호  이름  국어  영어  수학  총점  평균  석차");
				
				if (ar.size() > 0)
				{
					for (ScoreDTO dto : ar)
					{
						System.out.printf("  %s  %s  %d  %d  %d  %d  %2f  %d\n"
								         , dto.getSid()
								         , dto.getName()
								         , dto.getKor()
								         , dto.getEng()
								         , dto.getMat()
								         , dto.getTot()
								         , dto.getAvg()
								         , dto.getRank() );
								       
					}
				}
				else
				{
					System.out.println("검색 결과가 존재하지 않습니다.");
				}
				
				
			} while (true);
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		*/
		
		// 선생님 코드
		try
		{
			
			// 검색할 이름 입력받기
			sc = new Scanner(System.in);
			
			System.out.print("검색할 이름 입력 : ");
			String name = sc.next();
			
			//-- 필요한 경우 이 과정에서 프로그래밍적으로 검증(검사) 수행
			
			// 데이터베이스 연결
			dao.connection();
			
			// dao 의 list() 메소드 호출 → 매개변수로 검색할 이름 넘겨주기 
			ArrayList<ScoreDTO> arrayList = dao.lists(name);
			
			// 0 보다 작으면 검색결과가 존재하지 않는다.
			if (arrayList.size() > 0)
			{
				System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %4s %5d %4d %4d %5d %5.1f %5d\n"
							 , dto.getSid()
							 , dto.getName()
							 , dto.getKor()
							 , dto.getEng()
							 , dto.getMat()
							 , dto.getTot()
							 , dto.getAvg()
							 , dto.getRank());
				}
			}
			else
			{
				System.out.println("검색 결과가 존재하지 않습니다.");
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	// - 성적 수정
	public void sungjukUpdate()
	{
		/*
		try
		{
			
			// 데이터베이스 연결
			dao.connection();
			
			sc = new Scanner(System.in);
			
			do
			{
				
				System.out.print("번호 입력 : ");
				int sid = sc.nextInt();
				
				ArrayList<ScoreDTO> ar = dao.lists();
				
				
				
				
				
			} while (true);
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		*/
		
		// 선생님 코드
		try
		{
			
			// 수정할 학생의 번호 입력
			sc = new Scanner(System.in);
			
			System.out.print("수정할 번호 입력 : ");
			int sid = sc.nextInt();
			
			//-- 입력받은 번호로 체크(검증)해야 할 로직 적용 및 삽입 가능
			
			// 데이터베이스 연결
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList =dao.lists(sid);
			
			if (arrayList.size() > 0)
			{
				
				System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %4s %5d %4d %4d %5d %5.1f %5d\n"
							 , dto.getSid()
							 , dto.getName()
							 , dto.getKor()
							 , dto.getEng()
							 , dto.getMat()
							 , dto.getTot()
							 , dto.getAvg()
							 , dto.getRank());
				}
				
				// 수정할 값 입력받기
				System.out.print("수정 데이터 입력(이름 국어 영어 수학) : ");
				String name = sc.next();
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				ScoreDTO dto = new ScoreDTO();
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				dto.setSid(String.valueOf(sid)); 	// 문자열로 바인딩 check~!!!
				
				int result = dao.modify(dto);
				
				if (result > 0)
				{
					System.out.println(">> 수정이 완료되었습니다.");
				}
				
			}
			else
			{
				System.out.println("수정 대상이 존재하지 않습니다.");
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	// - 성적 삭제
	public void sungjukDelete()
	{
		try
		{
			
			// 삭제할 데이터의 학생번호 입력
			sc = new Scanner(System.in);
			
			System.out.print(">> 삭제할 번호를 입력 : ");
			int sid = sc.nextInt();
			
			//- 필요한 유효성 검사 수행
			
			// 데이터베이스 연결
			dao.connection();
			
			// dao 의 list() 메소드 호출 → sid 를 매개변수로 넘기기
			ArrayList<ScoreDTO> arrayList = dao.lists(sid);
			
			if (arrayList.size() > 0)
			{
				System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %4s %5d %4d %4d %5d %5.1f %5d\n"
							 , dto.getSid()
							 , dto.getName()
							 , dto.getKor()
							 , dto.getEng()
							 , dto.getMat()
							 , dto.getTot()
							 , dto.getAvg()
							 , dto.getRank());
				}
				
				System.out.print(">> 정말 삭제하시겠습니까?(Y/N) : ");
				String response = sc.next();		// Y or y or N or n etc...
													// -    -
				
				if (response.equals("Y") || response.equals("y"))
				{
					int result = dao.remove(sid);
					if (result > 0)
						System.out.println(">> 삭제가 완료되었습니다.");
				}
				else
				{
					System.out.println(">> 취소되었습니다.");
				}
				
			}
			else
			{
				System.out.println(">> 삭제 대상이 존재하지 않습니다.");
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
