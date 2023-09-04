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
import java.util.ArrayList;
import java.util.List;


/**
 * 该servlet由导航栏中任务管理模块的查看新/在研任务调用
 * 1.查看是否在会话期内，若否，则返回登录界面；
 * 2.若是，则获取用户参数
 * 3.若用户选择了项目，则调用TaskDao的queryTaskByPID_OwnerID_Status
 * 4.若为选择项目，则调用TaskDao的queryTaskByOwnerIDandStatus
 * 5.将结果保存在request域中
 * 6.请求转发到myTaskLook.jsp
 */
public class MyTaskLookServlet extends HttpServlet {

    private TaskService  taskService = new TaskServiceImpl();
    private ProjectService projectService = new ProjectServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/ITMS/pages/user/login.jsp");
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            String selectedPID = req.getParameter("selectedPID");
            String OwnerID = user.getU_ID();

            Integer t_Status = Integer.valueOf(req.getParameter("t_Status"));



            List<Task> tasks = taskService.queryTaskByOwnerIDandStatus(OwnerID, t_Status);
            if(selectedPID!=null && selectedPID!=""){
                List<Task> tasks_filter_byPID = new ArrayList<Task>();
                for(int i=0;i<tasks.size();i++){
                    Task task = tasks.get(i);
                    if(task.getT_PID().equals(selectedPID)){
                        tasks_filter_byPID.add(task);
                    }
                }
                Project project = projectService.queryProjectByProjectID(selectedPID);
                String selectedPName = project.getP_Name();
                req.setAttribute("selectedPName",selectedPName);
                req.setAttribute("selectedPID",selectedPID);
                req.setAttribute("tasks",tasks_filter_byPID);
            }
            else {
                req.setAttribute("tasks",tasks);
            }
            req.setAttribute("t_Status",t_Status);
            req.getRequestDispatcher("/pages/task/myTaskLook.jsp").forward(req,resp);
        }
    }
}
