package com.itms.servlet;

import com.itms.Bean.News;
import com.itms.Bean.Task;
import com.itms.Bean.User;
import com.itms.service.NewsService;
import com.itms.service.TaskService;
import com.itms.service.impl.NewsServiceImpl;
import com.itms.service.impl.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * 该servlet由myNewsLookServlet重定向调用？或者应该由用户点击导航栏过来？然后再进入MyNewsLookServlet
 * 1.获取登录用户的u_ID
 * 2.添加用户的消息
 * 2.1 判断用户任务还有多久到期，若小于5天，就生成一条消息：任务***即将到期。请及时完成！
 *
 */
public class NewsGenerateServlet extends HttpServlet {

    private NewsService newsService = new NewsServiceImpl();
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
        }
        else {

            //doingPorjectLook.jsp的提醒领取，提醒之后，仍然返回doingProjectLook.jsp
            //doingProjectLook.jsp的催促，催促之后，仍然返回doingProjectLook.jsp
            //二者只是消息的内容不同
            String p_ID = req.getParameter("p_ID");
            String t_ID = req.getParameter("t_ID");
            String btnType  =req.getParameter("btnType");
            Task task = taskService.queryTaskByTaskID(t_ID);
            String n_ToUID = task.getT_OwnerID();
            String n_Text;
            String getInfo="";
            if(btnType.equals("getTask")){
                 n_Text = "【通知】您的任务【"+task.getT_Name()+"】还未领取，请及时领取任务！";
                 getInfo="提醒成功！";
            }
            else {
                 n_Text = "【催促】请您尽快完成任务【"+task.getT_Name()+"】!";
            }
            News news = new News();
            news.setN_ToUID(n_ToUID);
            news.setN_Status(0);
            news.setN_ObjID(t_ID);
            news.setN_Text(n_Text);
            newsService.addNews(news);
            //由项目经理发出的消息通知不需要设置5days
//            task.setT_5days(1);
            String getInfo_UTF8 = URLEncoder.encode(getInfo,"UTF-8");
            resp.sendRedirect("/ITMS/doingProjectDetailsServlet?p_ID="+p_ID+"&getInfo="+getInfo_UTF8);
        }
    }

}
