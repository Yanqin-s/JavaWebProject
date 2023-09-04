package com.itms.Dao.impl;

import com.itms.Bean.Project;
import com.itms.Dao.ProjectDao;

import java.util.List;


public class ProjectDaoImpl extends BaseDao implements ProjectDao{


    @Override
    public Project queryProjectByProjectName(String projectName) {
        String sql = "select * from project where p_Name = ?";
        Project p = queryForOne(Project.class, sql, projectName);
        return p;
    }

    @Override
    public Project queryProjectByProjectID(String p_ID) {
        String sql = "select p.*, u.u_Name as p_MName " +
                "from `project` p join `user` u on p.p_MID = u.u_ID where p.p_ID = ?";
        Project p = queryForOne(Project.class, sql, p_ID);
        return p;
    }

    @Override
    public List<Project> queryProjectByP_MID(String p_MID, Integer p_Status) {
        String sql = "select p.*,u.u_Name as p_MName from project p join user u " +
                "on p.p_MID=u.u_ID and p.p_MID=? and p.p_Status=? ";

        return queryForList(Project.class, sql, p_MID, p_Status);
    }

    @Override
    public int updateProject(Project project) {
        String sql = "update project set " +
                "`p_AC` = ?, " +
                "`p_Status`  = ?," +
                "`p_StartDate` = ?," +
                "`p_Duration` = ?, " +
                "`p_5days` = ? " +
                " where `p_ID` = ?";
        return update(sql,
                project.getP_AC(),
                project.getP_Status(),
                project.getP_StartDate(),
                project.getP_Duration(),
                project.getP_5days(),
                project.getP_ID());
    }

    @Override
    public List<Project> queryProjectByMemberID(String u_ID) {
        String sql = "select p.p_Name as `p_Name`, p.p_ID as `p_ID` from " +
                "(select `t_PID` from `task` where `t_OwnerID`= ?) a " +
                "join `project` p on a.t_PID=p.p_ID;";
        return queryForList(Project.class,sql,u_ID);
    }
}
