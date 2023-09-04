package com.itms.servlet;

import com.itms.Bean.News;
import com.itms.Bean.Project;
import com.itms.Bean.Task;
import com.itms.Bean.User;
import com.itms.service.NewsService;
import com.itms.service.ProjectService;
import com.itms.service.TaskService;
import com.itms.service.UserService;
import com.itms.service.impl.NewsServiceImpl;
import com.itms.service.impl.ProjectServiceImpl;
import com.itms.service.impl.TaskServiceImpl;
import com.itms.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

public class AddTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, UnsupportedEncodingException {
        doPost(req, resp);
    }
    private TaskService taskService = new TaskServiceImpl();
    private ProjectService projectService = new ProjectServiceImpl();
    private NewsService newsService = new NewsServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/ITMS/pages/user/login.jsp");
        } else {
            //1.获取请求的参数：task的阶段、名称、描述、负责人并封装成javaBean对象

            Task task = new Task();

            String t_Period = req.getParameter("t_Period");
            String t_Name = req.getParameter("t_Name");
            String t_Description = req.getParameter("t_Description");
            Date t_DeadLine = Date.valueOf(req.getParameter("t_DeadLine"));
            Float t_Buget = Float.valueOf(req.getParameter("t_Buget"));
            String t_OwnerID = req.getParameter("t_OwnerID");
            String t_PID = req.getParameter("t_PID");
            //2.生成taskID:根据PID和t_Period和自增码生成t_ID
            //2.1.调用taskService的queryTaskByPIDandPeriod取出该项目阶段为t_Period的任务，并求个数
            List<Task> tasks_period = taskService.queryTaskByPIDandPeriod(t_PID, t_Period);
            Integer count = tasks_period.size();
            String t_IDLastTwoChars;
            //2.2 taskID最后两位为count+1，如果count+1<10 也要转成两位数
            if (count < 10) {
                t_IDLastTwoChars = "0" + count;
            } else {
                t_IDLastTwoChars = String.valueOf(count);
            }
            //2.3 生成taskID
            String t_ID = t_PID + t_Period + t_IDLastTwoChars;
            //3.调用taskService的addTask方法，task添加到task表中
            task.setT_ID(t_ID);
            task.setT_Name(t_Name);
            task.setT_Period(t_Period);
            task.setT_Description(t_Description);
            task.setT_DeadLine(t_DeadLine);
            task.setT_Budget(t_Buget);
            task.setT_OwnerID(t_OwnerID);
            task.setT_PID(t_PID);
            task.setT_Status(0);
            task.setT_AC(Float.valueOf(0));
            taskService.addTask(task);



            //4.查询当前项目下的所有任务
            List<Task> tasks = taskService.queryTaskByPIDandStatus(t_PID, 0);
            //5.查询当前所有员工
            Project project = projectService.queryProjectByProjectID(t_PID);
            project.setP_MName(user.getU_Name());
            //6.添加一条任务，给任务负责人发通知
            News news = new News();
            news.setN_ToUID(t_OwnerID);
            String n_Text = "【通知】您有一条来自项目【："+ project.getP_Name()+
                    "】的新任务:"+t_ID+"，及到”任务管理“-”新任务“模块中查看";
            news.setN_Text(n_Text);
            news.setN_Status(0);
            news.setN_ObjID(t_ID);
            newsService.addNews(news);
            //7.把结果保存到req域
            req.setAttribute("tasks", tasks);
            req.setAttribute("project", project);
            //8.请求转发到上一个界面
            //如果是新项目配置调用该servlet，则from=new；如果是在研项目添加任务，则from=doing
            String from = req.getParameter("from");
            if (from.equals("new")) {
                req.getRequestDispatcher("/pages/project/newProjectEdit.jsp").forward(req, resp);
            }
            if (from.equals("doing")) {
                resp.sendRedirect("/ITMS/doingProjectDetailsServlet?p_ID="+t_PID);
            }
        }
    }

}
