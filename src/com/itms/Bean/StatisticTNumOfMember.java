package com.itms.Bean;

public class StatisticTNumOfMember {
    private Integer taskNum;
    private String t_OwnerID;
    private String t_OwnerName;
    private Integer doneTaskNum;

    public StatisticTNumOfMember(){}

    public StatisticTNumOfMember(
            Integer taskNum,
            String t_OwnerID,
            String t_OwnerName,
            Integer doneTaskNum
    ){
        this.setTaskNum(taskNum);
        this.setT_OwnerName(t_OwnerName);
        this.setT_OwnerID(t_OwnerID);
        this.setDoneTaskNum(doneTaskNum);
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public String getT_OwnerID() {
        return t_OwnerID;
    }

    public void setT_OwnerID(String t_OwnerID) {
        this.t_OwnerID = t_OwnerID;
    }

    public String getT_OwnerName() {
        return t_OwnerName;
    }

    public void setT_OwnerName(String t_OwnerName) {
        this.t_OwnerName = t_OwnerName;
    }

    public Integer getDoneTaskNum() {
        return doneTaskNum;
    }

    public void setDoneTaskNum(Integer doneTaskNum) {
        this.doneTaskNum = doneTaskNum;
    }
}
