package com.itms.servlet;

import com.itms.Bean.Task;
import com.itms.Bean.User;
import com.itms.service.TaskService;
import com.itms.service.impl.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class EditTaskServlet extends HttpServlet {

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
            String t_ID = req.getParameter("t_ID");
            Task task = taskService.queryTaskByTaskID(t_ID);

            //根据路径读取该任务的文档
            String t_src = task.getT_Wendang();
            if(t_src!=null){
                File file = new File(t_src);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder stringBuilder = new StringBuilder();
                String temp = "";
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                String t_txt = stringBuilder.toString();
                task.setT_txt(t_txt);
            }

            String t_PID = task.getT_PID();
            List<Task> tasks = taskService.queryTaskByPID(t_PID);
            req.setAttribute("tasks",tasks);
            req.setAttribute("task",task);
            req.getRequestDispatcher("/pages/task/editTask.jsp").forward(req,resp);
        }
    }
}
