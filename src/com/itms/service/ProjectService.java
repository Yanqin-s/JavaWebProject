package com.itms.service;

import com.itms.Bean.Project;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ProjectService {
    /**
     * 由ProjectServiceImpl实现，调用projectDao的queryProjectByP_MID方法
     * @param U_ID
     * @param U_Status
     * @return 该用户的待领取/在研/已完成项目
     */
    public List<Project> queryProjectByP_MID(String U_ID, Integer U_Status);

    /**
     * 由ProjectServiceImpl实现，调用projectDao的updateProject方法
     * @param project
     * @return 修改成功则返回1，否则返回0
     */
    public int updateProject(Project project);

    /**
     * 根据p_ID查询project，主要是在edit新项目、查看在研项目详情时用到
     * @param p_ID
     * @return
     */
    public Project queryProjectByProjectID(String p_ID);

    /**
     * 查询自己所在的项目
     * @param u_ID
     * @return
     */
    public List<Project> queryProjectByMemberID(String u_ID);
}
