package com.itms.servlet;

import com.itms.Bean.Task;
import com.itms.Bean.User;
import com.itms.service.TaskService;
import com.itms.service.impl.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


/**
 * 该servlet需要保存editTask编辑的信息：preTask，postTask，当前成本（t_AC），最重要的是文档
 * 文档的保存：？采用page**插件，后台得到文本中的内容，再将文本内容保存为doc形式，导出到服务器中，设置t_Wendang的值为文档在服务器中的路径
 * 明天要做的：统计查询模块：某项任务的完成度，当前成本消耗(饼图，各个任务消耗比例)，某个成员任务完成比例，离deadLine只有**天的任务列表
 */
public class SaveTaskServlet extends HttpServlet {
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
            String t_PreTID = req.getParameter("t_PreTID");
            String t_PostTID = req.getParameter("t_PostTID");
            Float t_AC = Float.valueOf(req.getParameter("t_AC"));
            String t_Status = req.getParameter("t_Status");
            String t_txt = req.getParameter("t_Wendang");
            Task task = taskService.queryTaskByTaskID(t_ID);

            String path = this.getServletContext().getRealPath("")+ "/projectFiles/"+task.getT_PName() +"/"+task.getT_Name()+".txt";

            //将textarea内容输出到.txt中
            File file = new File(path);

            if(!file.exists()){

                file.getParentFile().mkdirs();
                try {
                    //创建文件
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(t_txt);
            fileWriter.close();

            task.setT_PreTID(t_PreTID);
            task.setT_PostTID(t_PostTID);
            task.setT_AC(t_AC);
            task.setT_Wendang(path);
            if(t_Status!=null){
                task.setT_Status(2);
                Date date = new Date(); // get the current date
                java.sql.Date t_AEndDate = new java.sql.Date(date.getTime());
                task.setT_AEndDate(t_AEndDate);
            }
            taskService.updateTask(task);

            PrintWriter out = resp.getWriter();
            out.println("<script>alert('修改成功')</script>");
            resp.sendRedirect(req.getContextPath()+"/taskDetailsServlet?t_ID=" + t_ID);

        }
    }
}
