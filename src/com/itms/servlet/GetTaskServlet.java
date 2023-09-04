package com.itms.servlet;

import com.itms.Bean.Task;
import com.itms.Bean.User;
import com.itms.service.TaskService;
import com.itms.service.impl.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class GetTaskServlet extends HttpServlet {

    private TaskService taskService = new TaskServiceImpl();
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
            String selectedPID = req.getParameter("selectedPID");
            String t_ID = req.getParameter("t_ID");
            Task task = new Task();
            task.setT_ID(t_ID);
            Date date = new Date(); // get the current date
            java.sql.Date t_StartDate = new java.sql.Date(date.getTime());
            task.setT_StartDate(t_StartDate);
            task.setT_AC(Float.valueOf(0));
            task.setT_Status(1);
            task.setT_5days(0);
            taskService.updateTask(task);

            resp.sendRedirect("/ITMS/myTaskLookServlet?t_Status=0&selectedPID="+selectedPID);
        }
    }
}
