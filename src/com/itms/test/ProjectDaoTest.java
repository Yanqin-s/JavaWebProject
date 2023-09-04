package com.itms.test;

import com.itms.Bean.Project;
import com.itms.Dao.ProjectDao;
import com.itms.Dao.impl.ProjectDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectDaoTest {

    private ProjectDao projectDao = new ProjectDaoImpl();

    @Test
    public void queryProjectByProjectName() {
        System.out.println(projectDao.queryProjectByProjectName("智能UI"));
    }

    @Test
    public void queryProjectByProjectID() {
    }

    @Test
    public void queryProjectByP_MID() {
    }

    @Test
    public void updateProject() {
    }
}