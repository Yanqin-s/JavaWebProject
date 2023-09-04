package com.itms.servlet;

import com.itms.Bean.Project;
import com.itms.Bean.User;
import com.itms.service.ProjectService;
import com.itms.service.impl.ProjectServiceImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ProjectLookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, UnsupportedEncodingException {
        doPost(req, resp);
    }
    private ProjectService projectService = new ProjectServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ///1.通过projectService查询全部某状态的project
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/ITMS/pages/user/login.jsp");
        } else {
            String userID = user.getU_ID();
            Integer p_Status = Integer.valueOf(req.getParameter("p_Status"));


            List<Project> projects = projectService.queryProjectByP_MID(userID, p_Status);
            //2.把全部结果保存到request域中
            req.setAttribute("projects", projects);
            req.setAttribute("p_Status", p_Status);
            //3.请求转发到/pages/project/newProjectLook.jsp页面
            req.getRequestDispatcher("/pages/project/projectLook.jsp").forward(req, resp);
        }
    }
}
