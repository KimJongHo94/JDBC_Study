/* ===========================================
 	    MemberDTO.java
 	    - 데이터 저장 및 전송 객체 구성 
   ===========================================
*/

package com.test;

public class MemberDTO
{
	// 주요 속성 구성
	private String sid, name, tel;
	
	// 이클립스 마우스 오른쪽 클릭 -> source -> generate getter / setter 선택 
	// getter / setter 구성 
	public String getSid()
	{
		return sid;
	}

	public void setSid(String sid)
	{
		this.sid = sid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}
	
	
}
