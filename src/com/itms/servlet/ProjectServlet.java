package com.itms.servlet;


import com.itms.Bean.Project;
import com.itms.Bean.User;
import com.itms.service.ProjectService;
import com.itms.service.impl.ProjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 该servlet用于project的相关操作：
 * 基本操作：
 * 1.查看某个用户的新/在研/已完成项目
 * 2.更改用户的项目的状态 分为两种情况：
 * 2.1 新项目→在研项目状态的改变：
 * 2.1.1 需要更新项目的开始时间P_StartDate（调用projectService的update方法）
 * 2.1.2 重定向？or请求转发？到任务配置界面xxx.jsp
 * 2.2 在研项目的改变：
 * 2.2.1 需要更新项目的历时P_Duration（调用projectService的update方法）
 * 额外操作：
 * 1.申请更改项目的deadline，跳转到申请界面xxx.jsp，填写申请理由(后期有时间完成)
 */
public class ProjectServlet extends BaseServlet {


    private ProjectService projectService = new ProjectServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        //根据session中用户的信息以及跳转的页面，是新项目：请求servlet的这个action，可以获得<a>的name吗？
        //通过<a>的name来判断是新项目请求的servlet还是在研项目请求的
        //如果是新项目的话，P_Status就是0，否则1
        //doget:先以iso-8859-1编码，再以utf-8解码
//        username = new String(username.getBytes("iso-8859-1"), "UTF-8");
        //dopost：设置请求体字符集utf-8
//        req.setCharacterEncoding("UTF-8");

        //1.通过projectService查询全部某状态的project
        User user = (User)req.getSession().getAttribute("user");
        if(user==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else{
            String userID = user.getU_ID();
            Integer P_Status = 0;
            String a_id = req.getParameter("id");
            if(a_id.equals("newProject")){
                P_Status = 0;
            }
            else if (a_id.equals("doingProject")){
                P_Status = 1;
            }
            else if (a_id.equals("doneProject")){
                P_Status = 2;
            }
            List<Project> projects = projectService.queryProjectByP_MID(userID,P_Status);
            //2.把全部结果保存到request域中
            req.setAttribute("projects",projects);
            //3.请求转发到/pages/project/newProjectLook.jsp页面
            req.getRequestDispatcher("/pages/project/projectLook.jsp").forward(req,resp);
        }

    }

}
