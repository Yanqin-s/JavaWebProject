package com.itms.Bean;



public class News {
    private Integer n_ID;//消息id
    private String n_Text;//消息的内容
    private Integer n_Status;//是否已读，0为未读，1为已读
    private String n_ToUID;//消息接收人
    private String n_ObjID;//消息中的taskOrProject

    public News(){}

    public Integer getN_ID() {
        return n_ID;
    }

    public void setN_ID(Integer n_ID) {
        this.n_ID = n_ID;
    }

    public String getN_Text() {
        return n_Text;
    }

    public void setN_Text(String n_Text) {
        this.n_Text = n_Text;
    }

    public Integer getN_Status() {
        return n_Status;
    }

    public void setN_Status(Integer n_Status) {
        this.n_Status = n_Status;
    }

    public String getN_ToUID() {
        return n_ToUID;
    }

    public void setN_ToUID(String n_ToUID) {
        this.n_ToUID = n_ToUID;
    }

    public String getN_ObjID() {
        return n_ObjID;
    }

    public void setN_ObjID(String n_ObjID) {
        this.n_ObjID = n_ObjID;
    }
}
