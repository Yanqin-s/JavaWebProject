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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 该servlet用于处理项目成员、项目经理查看任务详情
 * 1.判断是否在会话期内，若不在，则重新登录
 * 2.如果在，则获取前端传来的参数task_ID
 * 3.根据taskID查询该任务的详细信息
 * 4.项目经理视角：由doingProjectLook中的查看详情调用，传递的参数有：taskID，projectID
 * 5.任务负责人视角：myTaskLook中的查看详情调用
 */
public class TaskDetailsServlet extends HttpServlet {

    private TaskService taskService = new TaskServiceImpl();
    private ProjectService projectService = new ProjectServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        this.doPost(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/ITMS/pages/user/login.jsp");
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            String t_ID = req.getParameter("t_ID");


            Task task = taskService.queryTaskByTaskID(t_ID);
            String p_ID = task.getT_PID();
            //根据路径读取该任务的文档
            String t_src = task.getT_Wendang();
            if(t_src!=null && t_src.equals("")==false){
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


            Project project = projectService.queryProjectByProjectID(p_ID);
            req.setAttribute("task",task);
            //保存PID只是为了判断查看任务详细的人是项目经理（p_MID）还是项目成员
            req.setAttribute("project",project);
            req.getRequestDispatcher("/pages/task/taskDetails.jsp").forward(req,resp);
        }
    }
}
