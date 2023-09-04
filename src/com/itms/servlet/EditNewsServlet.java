package com.itms.servlet;

import com.itms.Bean.User;
import com.itms.service.NewsService;
import com.itms.service.ProjectService;
import com.itms.service.TaskService;
import com.itms.service.impl.NewsServiceImpl;
import com.itms.service.impl.ProjectServiceImpl;
import com.itms.service.impl.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 该servlet由页面myNewsLook.jsp调用
 * sendRedirect到MyNewsLookServlet（需要n_Status参数）
 * 处理过程：
 * 1.获取表单的参数：被选中的消息，操作的类别btnType，需要传递给下一个servlet的n_Status
 * 2.判别操作
 */

public class EditNewsServlet extends HttpServlet {

    private NewsService newsService = new NewsServiceImpl();

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
            String[] n_IDs = req.getParameterValues("selectedNews");
            String n_Status = req.getParameter("n_Status");
            String btnType = req.getParameter("btnType");

            for(int i=0;i<n_IDs.length;i++){
                if(btnType.equals("1")){
                    newsService.deleteNews(Integer.valueOf(n_IDs[i]));
                }else {
                    newsService.updateNews(Integer.valueOf(n_IDs[i]));
                }
            }
            resp.sendRedirect("/ITMS/myNewsLookServlet?n_Status="+n_Status);
        }
    }
}
