package com.itms.servlet;

import com.itms.Bean.Project;
import com.itms.Bean.Task;
import com.itms.Bean.User;
import com.itms.service.ProjectService;
import com.itms.service.TaskService;
import com.itms.service.UserService;
import com.itms.service.impl.ProjectServiceImpl;
import com.itms.service.impl.TaskServiceImpl;
import com.itms.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class DeleteTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, UnsupportedEncodingException {
        doPost(req, resp);
    }
    private TaskService taskService = new TaskServiceImpl();
    private UserService userService = new UserServiceImpl();
    private ProjectService projectService = new ProjectServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/ITMS/pages/user/login.jsp");
        } else {
            String t_ID = req.getParameter("t_ID");
            taskService.deleteTask(t_ID);
            String p_ID = req.getParameter("p_ID");
            Project project = projectService.queryProjectByProjectID(p_ID);
            List<Task> tasks = taskService.queryTaskByPIDandStatus(p_ID, 0);

            req.setAttribute("tasks", tasks);
            req.setAttribute("project", project);
            req.getRequestDispatcher("/pages/project/newProjectEdit.jsp").forward(req, resp);
        }
    }
}
