package com.itms.servlet;

import com.itms.Bean.Project;
import com.itms.Bean.Task;
import com.itms.Bean.User;
import com.itms.service.ProjectService;
import com.itms.service.TaskService;
import com.itms.service.impl.ProjectServiceImpl;
import com.itms.service.impl.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MultiProjectStatistic extends HttpServlet {
    private ProjectService projectService = new ProjectServiceImpl();
    private TaskService taskService = new TaskServiceImpl();
    private Date date = new Date(); // get the current date
    private Long curDate = date.getTime();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/ITMS/pages/user/login.jsp");
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            List<Project> projects = (List<Project>) req.getSession().getAttribute("inProjects");
            for (int i=0;i<projects.size();i++){
                Project project=new Project();
                String pID = project.getP_ID();
                List<Task> tasks = taskService.queryTaskByPID(pID);
                List<Task> tasks_2 = taskService.queryTaskByPIDandStatus(pID,2);
                Float fillRate = Float.valueOf(tasks_2.size()/tasks.size());
                project.setP_FillRate(fillRate);
            }
            req.setAttribute("projects",projects);
            req.getRequestDispatcher("/pages/statistic/MultiProjectStatistic.jsp").forward(req,resp);
        }
    }
}
