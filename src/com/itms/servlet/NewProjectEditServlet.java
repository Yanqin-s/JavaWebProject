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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class NewProjectEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        this.doPost(req, resp);
    }
    private ProjectService projectService = new ProjectServiceImpl();
    private TaskService taskService = new TaskServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/ITMS/pages/user/login.jsp");
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
        else {
            //1.根据req域中传来的projectid查询项目
            String projectID = req.getParameter("p_ID");
            Project project = projectService.queryProjectByProjectID(projectID);
            List<Task> tasks = taskService.queryTaskByPIDandStatus(projectID,0);
            //2.把查询到的结果保存在request域中,把负责人姓名保存到req域中
            req.setAttribute("tasks", tasks);
            req.setAttribute("project", project);
            //3.更改该项目的状态，作为已经领取p_Status=1;更改项目的开始时间，以领取时间为开始时间
            project.setP_Status(1);
            Date date = new Date(); // get the current date
            java.sql.Date p_StartDate = new java.sql.Date(date.getTime());
            project.setP_StartDate(p_StartDate);
            projectService.updateProject(project);
            //3.请求转发到/pages/project/newProjectEdit.jsp页面

            req.getRequestDispatcher("/pages/project/newProjectEdit.jsp").forward(req, resp);
        }
    }
}
