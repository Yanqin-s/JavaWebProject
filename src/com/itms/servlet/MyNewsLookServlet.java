package com.itms.servlet;

import com.itms.Bean.News;
import com.itms.Bean.User;
import com.itms.service.NewsService;
import com.itms.service.impl.NewsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 该servlet用于用户查看消息
 * 1.判断是否处于会话期
 * 2.获取参数已读/未读？userID
 * 3.调用newsDao方法查询
 * 4.将查询结果保存在request域中
 * 5.跳转到myNewsLook.jsp
 */
public class MyNewsLookServlet extends HttpServlet {

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
            String n_ToUID = user.getU_ID();
            Integer n_Status = Integer.valueOf(req.getParameter("n_Status"));
            List<News> newsList = newsService.queryNewsByToUIDandStatus(n_ToUID,n_Status);
            req.setAttribute("n_Status",n_Status);
            req.setAttribute("newsList", newsList);
            req.getRequestDispatcher("/pages/news/myNewsLook.jsp").forward(req,resp);

        }
    }
}
