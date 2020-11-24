package com.test;

import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;
public class Process
{
   // 주요 속성 구성 → 데이터베이스 액션 처리 전담 객체 → DAO
      private MemberDAO dao;
      private Scanner sc;
      
      // 생성자 정의 → 사용자 정의 생성자
      public Process()
      {
         dao = new MemberDAO();
      }
      
   // 1번 눌렀을때
      public void jikwonInsert() throws SQLException
      {
           sc = new Scanner(System.in);
           dao.connection();
           
           System.out.println("직원 정보 입력 ------------------------------------");
           System.out.print(" 이름 : ");
           String name = sc.next();
           
           System.out.print("주민등록번호(yymmdd-nnnnnnn) : "); 
           String ssn = sc.next();
           
           System.out.print("입사일 (YYYY-MM-DD) : "); 
           String ibsadate = sc.next();
           
           ArrayList<String> arr = dao.getData("city");
           
           System.out.print("지역( ");
           for (String s : arr) {
              System.out.print(s + "/"); 
           }
           System.out.print(") : ");
           String city = sc.next();
           
           System.out.print("전화번호 : "); 
           String tel = sc.next();
           
           arr = dao.getData("buseo");
           System.out.print("부서( ");
           for (String s : arr) {
              System.out.print(s + "/"); 
           }
           System.out.print(") : ");
           String buseo = sc.next();
           
           
           arr = dao.getData("jikwi");
           System.out.print("직위( ");
           for (String s : arr) {
              System.out.print(s + "/"); 
           }
           System.out.print(") : ");
           String jikwi = sc.next();
           
           
           int minbasicpay = dao.minbasicpay(jikwi);
           
           System.out.printf("기본급(최소 %d 이상) : ", minbasicpay); 
            int basicpay = sc.nextInt();
           
           System.out.print("수당 : "); 
           int sudang = sc.nextInt();
           
           int buseo_id = dao.SearchBuseoID(buseo);
           int city_id = dao.SearchCityID(city);
           int jikwi_id = dao.SearchJikwiID(jikwi);
           
           MemberDTO dto = new MemberDTO();
           
           dto.setName(name); dto.setSsn(ssn); dto.setIbsadate(ibsadate);
           dto.setCity(city); dto.setTel(tel); dto.setBuseo(buseo); dto.setJikwi(jikwi);
           dto.setBasicpay(basicpay); dto.setSudang(sudang);dto.setBuseo_id(buseo_id);
           dto.setCity_id(city_id);dto.setJikwi_id(jikwi_id);
           
           int result = dao.add(dto);
      }
      
      // 2번 눌렀을때 
      public void jikwonSelectAll()
      {
         try
            {
               // 데이터베이스 연결
               dao.connection();
                        
               sc = new Scanner(System.in);
               
               System.out.println();
               System.out.println("1. 사번 정렬");
               System.out.println("2. 이름 정렬");
               System.out.println("3. 부서 정렬");
               System.out.println("4. 직위 정렬");
               System.out.println("5. 급여 내림차순 정렬");
               System.out.print(">> 선택(1~5, -1종료) : ");
               int orderby = sc.nextInt();
                        
               int count = dao.count();
               
               System.out.println();
               System.out.printf("전체 인원 : %d명\n", count);
               System.out.println("사번    이름     주민번호    입사일  지역  전화번호  부서  직위  기본급  수당  급여");
               
               for (MemberDTO dto : dao.lists(orderby))
               {
                  System.out.printf("%5d %4s %15s %15s %6s %15s %6s %6s %8d %8d %8d\n"
                        , dto.getEmp_id(), dto.getName()
                        , dto.getSsn(), dto.getIbsadate(), dto.getCity()
                        , dto.getTel(), dto.getBuseo(), dto.getJikwi()
                        , dto.getBasicpay(), dto.getSudang(), dto.getPay());
               }
               
               dao.close();
               
               
            } catch (Exception e)
            {
               System.out.println(e.toString());
            }

      }
      
      // 3번 눌렀을때
      public void jikwonSearch() throws SQLException
      {
          String[] type =
               { "", "사번", "이름", "부서", "직위" };
               sc = new Scanner(System.in);

               // 메뉴출력
               for (int i = 1; i < type.length; ++i)
               {
                  System.out.printf("%d. %s 검색\n", i, type[i]);
               }

               System.out.print("원하는 검색 속성 번호를 입력하세요 : ");
               int index = sc.nextInt();

               System.out.printf("검색하실 %s을(를) 입력하세요 : ", type[index]);
               String search = sc.next();

               // db 접속
               dao.connection();
               ArrayList<MemberDTO> arrObj = dao.searchLists(index, search);

               if (arrObj.size() > 0)
               {
                  for (MemberDTO obj : arrObj)
                  {
                     System.out.printf("%5d %4s %15s %15s %6s %15s %6s %6s %8d %8d %8d\n", obj.getEmp_id(), obj.getName(),
                           obj.getSsn(), obj.getIbsadate(), obj.getCity(), obj.getTel(), obj.getBuseo(), obj.getJikwi(),
                           obj.getBasicpay(), obj.getSudang(), obj.getPay());
                  }

               } else
               {
                  System.out.println("검색 결과가 없습니다.");
               }

               // db 해제
               dao.close();

      }
      
      // 4번 눌렀을때
      // 4번 눌렀을때
       public void jikwonUpdate() throws SQLException
       {
           // 수정할 직원의 번호 입력
          sc = new Scanner(System.in);
          System.out.print("수정할 직원의 사원번호를 입력하세요 : ");
          int emp_id = sc.nextInt();
          
          // 데이터베이스 연결
          dao.connection();
          
          ArrayList<MemberDTO> arrayList = dao.lists1(emp_id);
          
          if (arrayList.size() > 0)
         {
            System.out.println("사번   이름   주민번호   입사일  지역  전화번호  부서  직위  기본급  수당  급여");
            for (MemberDTO dto : arrayList)
            {
               System.out.printf("%2d %4s %4s %4s %3s %3s %4s %3s %3s %2d %2d"
                     , dto.getEmp_id(), dto.getName(), dto.getSsn(), dto.getIbsadate()
                     , dto.getCity(), dto.getTel(), dto.getBuseo(), dto.getJikwi()
                     , dto.getBasicpay(), dto.getSudang(), dto.getPay());
            }
            
            System.out.println();
            System.out.print("수정 데이터 입력(이름 주민번호 입사일 지역번호 전화번호 부서번호 직위번호 기본급 수당 : ");
            String name = sc.next();
            String ssn = sc.next();
            String ibsadate = sc.next();
            int city_id = sc.nextInt();
            String tel = sc.next();
            int buseo_id = sc.nextInt();
            int jikwi_id = sc.nextInt();
            int basicpay = sc.nextInt();
            int sudang = sc.nextInt();
            
            MemberDTO dto = new MemberDTO();
            dto.setName(name);
            dto.setSsn(ssn);
            dto.setIbsadate(ibsadate);
            dto.setCity_id(city_id);
            dto.setTel(tel);
            dto.setBuseo_id(buseo_id);
            dto.setJikwi_id(jikwi_id);
            dto.setBasicpay(basicpay);
            dto.setSudang(sudang);
            dto.setEmp_id(emp_id);
            
            int result = dao.update(dto);
            if (result > 0)
            {
               System.out.println("정상적으로 입력되었습니다.");
            }
            
            dao.close();
             
         }
          else
          {
            System.out.println("수정할 대상이 없습니다.");
         }
          
          
          
       }
      
      // 5번 눌렀을때
       public void jikwonDelete()
       {
          try
             {
                sc = new Scanner(System.in);   
                System.out.print("삭제할 사원의 번호를 입력하세요 : ");
                String emp_id = sc.next();
                
                dao.connection();
                
                
                ArrayList<MemberDTO> arrayList = dao.searchLists(1, emp_id);
                
                if(arrayList.size() > 0)      
                {
                   
                   System.out.println("사번 이름   주민번호   입사일   지역   전화번호   부서   직위   기본급   수당   급여");
                   
                   
                   for(MemberDTO dto : arrayList)
                   {
                      System.out.printf("%3s %4s %5s %4s %4s %5s %3s %d %d\n"
                            , dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity_id()
                            , dto.getTel(), dto.getBuseo_id(), dto.getJikwi_id(), dto.getBasicpay(), dto.getSudang());
                   }
                   System.out.print("정말 삭제하시겠습니까?(Y/N) : ");
                   String response = sc.next();      
                   
                   if(response.equals("Y") || response.equals("y"))
                   {
                      int result = dao.remove(Integer.parseInt(emp_id));
                      if(result > 0)
                      {
                         System.out.println("삭제가 완료되었습니다.");
                      }
                   }
                   else
                   {
                      System.out.print(">>삭제가 취소되었습니다.");
                   }
                }
                else   
                {
                   System.out.println("삭제할 대상이 존재하지 않습니다.");
                }
             
             dao.close();
             
             }catch(Exception e)
             {
                System.out.println(e.toString());
             }
       }
}