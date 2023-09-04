package com.itms.Bean;

import java.util.Date;

public class Task {
    private String t_ID;
    private String t_Name;
    private String t_Description;
    private String t_PreTID;
    private String t_PostTID;
    private Date t_StartDate;
    private Date t_DeadLine;
    private Date t_Duration;
    private Float t_Budget;
    private Float t_AC;
    private Integer t_Status;
    private String t_Evaluation;
    private String t_Wendang;
    private String t_PID;
    private String t_OwnerID;
    private String t_Period;
    private String t_OwnerName;
    private String t_PName;
    private String t_txt;
    private Long t_DiffDay;

    public Long getT_EarlierDays() {
        return t_EarlierDays;
    }

    public void setT_EarlierDays(Long t_EarlierDays) {
        this.t_EarlierDays = t_EarlierDays;
    }

    private Long t_EarlierDays;


    private Integer t_5days;

    public Date getT_AEndDate() {
        return t_AEndDate;
    }

    public void setT_AEndDate(Date t_AEndDate) {
        this.t_AEndDate = t_AEndDate;
    }

    private Date t_AEndDate;




    public Task(
            String t_ID,
            String t_Name,
            String t_Description,
            Date t_DeadLine,
            Float t_Buget,
            Integer t_Status,
            String t_PID,
            String t_OwnerID,
            String t_OwnerName,
            String t_Period
    ){
        this.setT_ID(t_ID);
        this.setT_Name(t_Name);
        this.setT_Description(t_Description);
        this.setT_DeadLine(t_DeadLine);
        this.setT_Budget(t_Buget);
        this.setT_Status(t_Status);
        this.setT_PID(t_PID);
        this.setT_OwnerID(t_OwnerID);
        this.setT_OwnerName(t_OwnerName);
        this.setT_Period(t_Period);
    }
    public Task(){}

    public String getT_ID() {
        return t_ID;
    }

    public void setT_ID(String t_ID) {
        this.t_ID = t_ID;
    }

    public String getT_Name() {
        return t_Name;
    }

    public void setT_Name(String t_Name) {
        this.t_Name = t_Name;
    }

    public String getT_Description() {
        return t_Description;
    }

    public void setT_Description(String t_Description) {
        this.t_Description = t_Description;
    }

    public String getT_PreTID() {
        return t_PreTID;
    }

    public void setT_PreTID(String t_PreTID) {
        this.t_PreTID = t_PreTID;
    }

    public String getT_PostTID() {
        return t_PostTID;
    }

    public void setT_PostTID(String t_PostTID) {
        this.t_PostTID = t_PostTID;
    }

    public Date getT_StartDate() {
        return t_StartDate;
    }

    public void setT_StartDate(Date t_StartDate) {
        this.t_StartDate = t_StartDate;
    }

    public Date getT_DeadLine() {
        return t_DeadLine;
    }

    public void setT_DeadLine(Date t_DeadLine) {
        this.t_DeadLine = t_DeadLine;
    }

    public Date getT_Duration() {
        return t_Duration;
    }

    public void setT_Duration(Date t_Duration) {
        this.t_Duration = t_Duration;
    }

    public Float getT_Budget() {
        return t_Budget;
    }

    public void setT_Budget(Float t_Budget) {
        this.t_Budget = t_Budget;
    }

    public Float getT_AC() {
        return t_AC;
    }

    public void setT_AC(Float t_AC) {
        this.t_AC = t_AC;
    }

    public Integer getT_Status() {
        return t_Status;
    }

    public void setT_Status(Integer t_Status) {
        this.t_Status = t_Status;
    }

    public String getT_Evaluation() {
        return t_Evaluation;
    }

    public void setT_Evaluation(String t_Evaluation) {
        this.t_Evaluation = t_Evaluation;
    }

    public String getT_Wendang() {
        return t_Wendang;
    }

    public void setT_Wendang(String t_Wendang) {
        this.t_Wendang = t_Wendang;
    }

    public String getT_PID() {
        return t_PID;
    }

    public void setT_PID(String t_PID) {
        this.t_PID = t_PID;
    }

    public String getT_OwnerID() {
        return t_OwnerID;
    }

    public void setT_OwnerID(String t_OwnerID) {
        this.t_OwnerID = t_OwnerID;
    }

    public String getT_Period() {
        return t_Period;
    }

    public void setT_Period(String t_Period) {
        this.t_Period = t_Period;
    }
    public String getT_OwnerName() {return t_OwnerName;}

    public void setT_OwnerName(String t_OwnerName) {this.t_OwnerName = t_OwnerName;}

    public String getT_PName() {return t_PName;}

    public void setT_PName(String t_PName) {this.t_PName = t_PName;}

    public String getT_txt() {
        return t_txt;
    }

    public void setT_txt(String t_txt) {
        this.t_txt = t_txt;
    }

    public Integer getT_5days() {
        return t_5days;
    }

    public void setT_5days(Integer t_5days) {
        this.t_5days = t_5days;
    }

    public Long getT_DiffDay() {
        return t_DiffDay;
    }

    public void setT_DiffDay(Long t_DiffDay) {
        this.t_DiffDay = t_DiffDay;
    }
}
