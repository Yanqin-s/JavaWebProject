/*
 * @Author: Amber.Shuai
 * @Github: https://github.com/Yanqin-s
 * @Date: 2021-12-06 21:50:59
 * @LastEditord: Amber.shuai
 * @LastEditTime: 2021-12-06 23:33:27
 */
package com.itms.Bean;

public class User {
	private String u_Name;
	private String u_PW;
	private String u_ID;
	private String u_Gender;
	private String u_BOD;
	private String u_MP;
	private String u_PostRank;
	private String u_Post;
	private String u_JoinDate;
	private Float u_Salary;

	public User(String u_Name, String u_PW, String u_ID, String u_Gender, String u_BOD,
				String u_MP, String u_PostRank, String u_Post, String u_JoinDate, Float u_Salary) {
		this.setU_Name(u_Name);
		this.setU_PW(u_PW);
		this.setU_ID(u_ID);
		this.setU_Gender(u_Gender);
		this.setU_BOD(u_BOD);
		this.setMobilePhone(u_MP);
		this.setPostRank(u_PostRank);
		this.setU_Post(u_Post);
		this.setJoinDate(u_JoinDate);
		this.setU_Salary(u_Salary);
	}

	

	public User() {}
	
	public User(String U_ID, String U_PW) {
		this.setU_PW(U_PW);
		this.setU_ID(U_ID);
	}
	



	public String getU_Gender() {
		return u_Gender;
	}

	public void setU_Gender(String u_Gender) {
		this.u_Gender = u_Gender;
	}

	public String getU_Name() {
		return u_Name;
	}

	public void setU_Name(String u_Name) {
		this.u_Name = u_Name;
	}

	public String getU_PW() {
		return u_PW;
	}

	public void setU_PW(String u_PW) {
		this.u_PW = u_PW;
	}

	public String getU_ID() {
		return u_ID;
	}

	public void setU_ID(String user_ID) {
		this.u_ID = user_ID;
	}

	public String getU_BOD() {
		return u_BOD;
	}

	public void setU_BOD(String bOD) {
		u_BOD = bOD;
	}


	public String getU_PostRank() {
		return u_PostRank;
	}

	public void setPostRank(String post_rank) {
		this.u_PostRank = post_rank;
	}

	public String getU_MP() {
		return u_MP;
	}

	public void setMobilePhone(String mobile_phone) {
		this.u_MP = mobile_phone;
	}

	public String getU_Post() {
		return u_Post;
	}

	public void setU_Post(String u_Post) {
		this.u_Post = u_Post;
	}

	public String getU_JoinDate() {
		return u_JoinDate;
	}

	public void setJoinDate(String join_date) {
		this.u_JoinDate = join_date;
	}

	public Float getU_Salary() {
		return u_Salary;
	}

	public void setU_Salary(Float u_Salary) {
		this.u_Salary = u_Salary;
	}

}