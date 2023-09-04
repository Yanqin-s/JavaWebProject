package com.itms.servlet;

import com.itms.Bean.Project;
import com.itms.Bean.StatisticTNumOfMember;
import com.itms.Bean.Task;
import com.itms.Bean.User;
import com.itms.service.NewsService;
import com.itms.service.ProjectService;
import com.itms.service.TaskService;
import com.itms.service.impl.NewsServiceImpl;
import com.itms.service.impl.ProjectServiceImpl;
import com.itms.service.impl.TaskServiceImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class SingleProjectStatistic extends HttpServlet {


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
            String selectedPID = req.getParameter("selectedPID");
            Project project = projectService.queryProjectByProjectID(selectedPID);
            if(project!=null){
                Long p_DeadDate = project.getP_DeadLine().getTime();
                Long p_DiffDay = (p_DeadDate-curDate)/(24*60*60*1000);
                project.setP_DiffDay(p_DiffDay);
            }


//            List<Project> projects = projectService.queryProjectByP_MID(p_MID,1);
            List<Task> tasks = taskService.queryTaskByPID(selectedPID);
            List<Task> tasks_0 = new ArrayList<>();
            List<Task> tasks_1 = new ArrayList<>();
            List<Task> tasks_2 = new ArrayList<>();
            Integer overBudgetNum=0;
            for(int i=0;i<tasks.size();i++){
                Task task = tasks.get(i);
                if(task.getT_AC()!=null && task.getT_AC()>task.getT_Budget()){
                    overBudgetNum++;
                }
                switch (task.getT_Status()){
                    case 0:{

                        tasks_0.add(task);
                        break;
                    }
                    case 1:{
                        Long t_Diffday = (task.getT_DeadLine().getTime()-curDate)/(24*60*60*1000);
                        task.setT_DiffDay(t_Diffday);
                        tasks_1.add(task);
                        break;
                    }
                    case 2:{
                        Long t_DeadDate = task.getT_DeadLine().getTime();
                        Long t_AEndDate = task.getT_AEndDate().getTime();
                        Long t_EarlierDays = (t_DeadDate-t_AEndDate)/(24*60*60*1000);
                        task.setT_EarlierDays(t_EarlierDays);
                        tasks_2.add(task);
                        break;
                    }
                }
            }
            Collections.sort(tasks_1,new Comparator<Task>(){

                @Override
                public int compare(Task o1, Task o2) {
                    // TODO Auto-generated method stub
                    return (int) (o1.getT_DiffDay()-o2.getT_DiffDay());
                }
            });
            Collections.sort(tasks_2,new Comparator<Task>(){

                @Override
                public int compare(Task o1, Task o2) {
                    // TODO Auto-generated method stub
                    return (int) (o1.getT_EarlierDays()-o2.getT_EarlierDays());
                }
            });
            req.setAttribute("OverBudgetNum", overBudgetNum);





//            List<Task> tasks_0 = taskService.queryTaskByPIDandStatus(selectedPID,0);
//            List<Task> tasks_1 = taskService.queryTaskByPIDandStatus(selectedPID,1);
//
//
//            List<Task> tasks_2 = taskService.queryTaskByPIDandStatus(selectedPID,2);
//            //查询每个用户在该项目中的任务数量,已完成的任务数量
            List<StatisticTNumOfMember> statisticTNumOfMemberList = taskService.queryByTPID(selectedPID);

            req.setAttribute("statisticTNumOfMemberList",statisticTNumOfMemberList);
            req.setAttribute("tasks_0",tasks_0);
            req.setAttribute("tasks_1", tasks_1);
            req.setAttribute("tasks_2",tasks_2);
            req.setAttribute("tasks",tasks);
            req.setAttribute("project",project);
//            req.setAttribute("projects",projects);
            req.setAttribute("selectedPID",selectedPID);
            req.getRequestDispatcher("/pages/statistic/singleProjectStatistic.jsp").forward(req,resp);
        }
    }
}
