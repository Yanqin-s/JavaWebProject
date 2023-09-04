package com.itms.Bean;

import java.sql.Date;

public class Project {
	private String p_Name;
	private String p_ID;
	private String p_Description;
	private Float p_Budget;
	private Float p_AC;//p_AC = the sum of each t_AC
	private Integer p_Status;
	private Date p_StartDate;
	private Date p_DeadLine;
	private Date p_Duration;
	private String p_Dpmt;
	private String p_MID;
	private String p_MName;

	private Long p_DiffDay;

	private Integer p_5days;

	public Float getP_FillRate() {
		return p_FillRate;
	}

	public void setP_FillRate(Float p_FillRate) {
		this.p_FillRate = p_FillRate;
	}

	private Float p_FillRate;

	public Project(
			String p_name,
			String p_ID,
			String p_Description,
			Float p_Budget,
			Float p_AC,
			Integer p_Status,
			Date p_StartDate,
			Date p_DeadLine,
			Date p_Duration,
			String p_Dpmt,
			String p_MID,
			String p_MName
			){
		this.p_Name = p_name;
		this.p_ID = p_ID;
		this.p_Description = p_Description;
		this.p_Budget = p_Budget;
		this.p_AC = p_AC;
		this.p_Status = p_Status;
		this.p_StartDate = p_StartDate;
		this.p_DeadLine = p_DeadLine;
		this.p_Duration = p_Duration;
		this.p_Dpmt = p_Dpmt;
		this.p_MID = p_MID;
		this.p_MName = p_MName;
	}

	public Project(){}

	public String getP_Name() {
		return p_Name;
	}

	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}

	public String getP_ID() {
		return p_ID;
	}

	public void setP_ID(String p_ID) {
		this.p_ID = p_ID;
	}

	public String getP_Description() {
		return p_Description;
	}

	public void setP_Description(String p_Description) {
		this.p_Description = p_Description;
	}

	public Float getP_Budget() {
		return p_Budget;
	}

	public void setP_Budget(Float p_Budget) {
		this.p_Budget = p_Budget;
	}

	public Float getP_AC() {
		return p_AC;
	}

	public void setP_AC(Float p_AC) {
		this.p_AC = p_AC;
	}

	public Integer getP_Status() {
		return p_Status;
	}

	public void setP_Status(Integer p_Status) {
		this.p_Status = p_Status;
	}

	public Date getP_StartDate() {
		return p_StartDate;
	}

	public void setP_StartDate(Date p_StartDate) {
		this.p_StartDate = p_StartDate;
	}

	public Date getP_DeadLine() {
		return p_DeadLine;
	}

	public void setP_DeadLine(Date p_DeadLine) {
		this.p_DeadLine = p_DeadLine;
	}

	public Date getP_Duration() {
		return p_Duration;
	}

	public void setP_Duration(Date p_Duration) {
		this.p_Duration = p_Duration;
	}

	public String getP_Dpmt() {
		return p_Dpmt;
	}

	public void setP_Dpmt(String p_Dpmt) {
		this.p_Dpmt = p_Dpmt;
	}

	public String getP_MID() {
		return p_MID;
	}

	public void setP_MID(String p_MID) {
		this.p_MID = p_MID;
	}
	public String getP_MName() {
		return p_MName;
	}

	public void setP_MName(String p_MName) {
		this.p_MName = p_MName;
	}

	public Integer getP_5days() {
		return p_5days;
	}

	public void setP_5days(Integer p_5days) {
		this.p_5days = p_5days;
	}

	public Long getP_DiffDay() {
		return p_DiffDay;
	}

	public void setP_DiffDay(Long p_DiffDay) {
		this.p_DiffDay = p_DiffDay;
	}
	@Override
	public String toString() {
		return "Project{" +
				"p_Name='" + p_Name + '\'' +
				", p_ID='" + p_ID + '\'' +
				", p_Description='" + p_Description + '\'' +
				", p_Budget=" + p_Budget +
				", p_AC=" + p_AC +
				", p_Status=" + p_Status +
				", p_StartDate=" + p_StartDate +
				", p_DeadLine=" + p_DeadLine +
				", p_Duration=" + p_Duration +
				", p_Dpmt='" + p_Dpmt + '\'' +
				", p_MID='" + p_MID + '\'' +
				", p_MName='" + p_MName + '\'' +
				'}';
	}
}
