package com.itms.service.impl;

import com.itms.Bean.Project;
import com.itms.Dao.ProjectDao;
import com.itms.Dao.impl.ProjectDaoImpl;
import com.itms.service.ProjectService;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    //实例化一个projectDao对象，调用Dao的方法，操作数据库
    ProjectDao projectDao = new ProjectDaoImpl();

    /**
     * 实现ProjectService方法，由ProjectServlet调用
     * @param u_ID:登录用户的ID
     * @param p_Status：请求的项目的状态类型
     * @return 返回projectDao对应方法的结果
     */
    @Override
    public List<Project> queryProjectByP_MID(String u_ID, Integer p_Status) {
        return projectDao.queryProjectByP_MID(u_ID,p_Status);
    }

    /**
     * 实现projectService的方法，由ProjectServlet调用,（但其实只是更新了项目的状态而已，更多的是往数据库的task写数据
     * @param project
     * @return 返回projectDao对应方法的结果
     */
    @Override
    public int updateProject(Project project) {
        return projectDao.updateProject(project);
    }

    @Override
    public Project queryProjectByProjectID(String p_ID) {
        return projectDao.queryProjectByProjectID(p_ID);
    }

    @Override
    public List<Project> queryProjectByMemberID(String u_ID) {
        return projectDao.queryProjectByMemberID(u_ID);
    }
}
