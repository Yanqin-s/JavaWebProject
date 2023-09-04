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
import java.sql.Date;
import java.util.List;


/**
 * 当点击某个在研项目的查看详情时，进入该servlet
 * 1.判断是否在会话期内，如果不是则返回登录界面
 * 2.如果在会话期内，获取projectID
 * 3.根据projectID查询project的信息
 * 4.根据projectID查询所有任务，task_0和task_1
 * 5.把project和task_0、task_1保存在request域中
 */
public class DoingProjectDetailsServlet extends HttpServlet {

    /**
     * 当点击某个在研项目的查看详情时，进入该servlet
     * 1.判断是否在会话期内，如果不是则返回登录界面
     * 2.如果在会话期内，获取projectID
     * 3.根据projectID查询project的信息
     * 4.根据projectID查询所有任务，task_0和task_1
     * 5.把project和task_0、task_1保存在request域中
     */
    private ProjectService projectService = new ProjectServiceImpl();
    private TaskService taskService = new TaskServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");


        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/ITMS/pages/user/login.jsp");
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            String projectID = req.getParameter("p_ID");
            String getInfo = req.getParameter("getInfo");
            Project project = projectService.queryProjectByProjectID(projectID);
            List<Task> tasks_0 = taskService.queryTaskByPIDandStatus(projectID,0);
            List<Task> tasks_1 = taskService.queryTaskByPIDandStatus(projectID,1);
            List<Task> tasks_2 = taskService.queryTaskByPIDandStatus(projectID,2);
            Float sumOfTask_1AC= Float.valueOf(0);
            Float sumOfTask_2AC = Float.valueOf(0);
            for(int i=0;i<tasks_1.size();i++){
                sumOfTask_1AC+=tasks_1.get(i).getT_AC();

            }
            for(int i=0;i<tasks_2.size();i++){
                sumOfTask_2AC+=tasks_2.get(i).getT_AC();
            }
            project.setP_AC(sumOfTask_1AC+sumOfTask_2AC);
            projectService.updateProject(project);

            req.setAttribute("tasks_0",tasks_0);
            req.setAttribute("tasks_1",tasks_1);
            req.setAttribute("tasks_2", tasks_2);
            req.setAttribute("project", project);
            req.setAttribute("getInfo",getInfo);

            req.getRequestDispatcher("/pages/project/doingProjectLook.jsp").forward(req,resp);


        }
    }
}
