/*
 * @Author: Amber.Shuai
 * @Github: https://github.com/Yanqin-s
 * @Date: 2021-12-07 11:21:27
 * @LastEditord: Amber.shuai
 * @LastEditTime: 2021-12-07 11:21:27
 */
package com.itms.servlet;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


/**
 * 1.获取请求的参数
 * 2.调用StaffService.login()处理业务
 * 3.根据login方法的结果判断是否登录成功
 * 4.若失败，跳回登录页面；
 *   若成功，进入页面，loginsuccess.html
 */

public class LoginServlet extends HttpServlet{
	private UserService userService = new UserServiceImpl();
	private ProjectService projectService = new ProjectServiceImpl();
	private TaskService taskService = new TaskServiceImpl();
	private NewsService newsService = new NewsServiceImpl();
	private Date date = new Date(); // get the current date
	private Long curDate = date.getTime();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. 获取请求参数
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String userID = req.getParameter("userID");
		String password = req.getParameter("password");
		User userReq = new User(userID,password);


		// 2. 调用service.login方法
		User loginUser = userService.login(userReq);


		if(loginUser==null){

			resp.sendRedirect("/ITMS/pages/user/login.jsp");

		}
		else{
			List<Project> projects =  new ArrayList<>();
			Integer user_type = 0;
			projects = projectService.queryProjectByMemberID(userID);
			if(projects.size()==0){
				//说明登录用户为项目经理
				user_type=1;
				projects = projectService.queryProjectByP_MID(userID,1);
			}
			List<User> users = userService.queryAllUserNameAndID();
			req.getSession().setAttribute("user_type",user_type);
			req.getSession().setAttribute("users",users);
			req.getSession().setAttribute("user", loginUser);
			req.getSession().setAttribute("inProjects",projects);

			//如果user是项目成员，检查一下该user的在研任务还有几天截止，小于5天并且t_5days=0(表示该项任务并没有被检查)，就生成一条news，发给任务负责人和项目负责人
			// 并将任务的t_5days设置为1
			//如果user是项目经理，检查该user的在研项目还有几天截止，小于5天并且p_5days=0，则生成一条警告发给项目经理，将p_5days=1
			//然后外循环：项目
			//   内循环：项目下的任务（t_5days=0），并且<5天，则给任务负责人还有项目经理都发通知，t_5days=1

			generateNewsByUser(loginUser);
			req.getRequestDispatcher("/pages/user/loginSuccessful.jsp").forward(req, resp);
		}
	}

	public void generateNewsByUser(User user){
//		Date date = new Date(); // get the current date
//		Long curDate = date.getTime();
		//只查看在研项目,在研任务
		//1.判断是不是项目经理，userID作为PID得到projects，若projectsSize=0，就是成员，若!=0，经理
		List<Project> projects = projectService.queryProjectByP_MID(user.getU_ID(), 1);
		if(projects.size()>0){
			//项目经理
			for(int i=0;i<projects.size();i++){
				Project project = projects.get(i);
				//判断项目还有几天到期
				Long p_DeadDate = project.getP_DeadLine().getTime();
				Long p_DiffDay = (p_DeadDate-curDate)/(24*60*60*1000);
				if(p_DiffDay<=5&&project.getP_5days()==0){
					News news = new News();
					news.setN_ToUID(user.getU_ID());
					news.setN_Status(0);
					String n_Text = "【警告】您的项目【"+project.getP_Name()+"】即将到期,请及时完成！";
					news.setN_Text(n_Text);
					news.setN_ObjID(project.getP_ID());
					newsService.addNews(news);
					project.setP_5days(1);//表明该项目已经检查过了，提醒之后，不再产生提示
					projectService.updateProject(project);
				}
				//检查项目所有没有被提醒的任务，不做表连接
				List<Task> tasks = taskService.queryTaskForNewsByPID(project.getP_ID());
				generateNewsByTask(tasks);
				}
			}

		//表明登录者是任务负责人，根据task的OwnerID查询所有未被提醒的在研任务(t_5days=0 and t_Status=1)
		if(projects.size()==0){
			List<Task> tasks = taskService.queryTaskForNewsByOwnerID(user.getU_ID());
			generateNewsByTask(tasks);
		}
	}

	public void generateNewsByTask(List<Task> tasks) {
		for(int i=0;i<tasks.size();i++){
			Task task = tasks.get(i);
			Long t_DeadDate = task.getT_DeadLine().getTime();
			Long t_DiffDay = (t_DeadDate - curDate) / (24 * 60 * 60 * 1000);
			if (t_DiffDay <= 5) {
				//发送给项目经理的通知
				Project project = projectService.queryProjectByProjectID(task.getT_PID());//查询task的项目
				News news_toPMID = new News();
				news_toPMID.setN_ToUID(project.getP_MID());//news发送给该项目经理
				news_toPMID.setN_Status(0);//设置news的初始状态
				String p_nText = "【通知】任务【" + task.getT_Name() + "】即将到期！";
				news_toPMID.setN_Text(p_nText);//news的内容
				news_toPMID.setN_ObjID(task.getT_ID());//news涉及到的任务ID
				newsService.addNews(news_toPMID);
				//发送给任务负责人的通知
				News news_toTMID = new News();
				news_toTMID.setN_ToUID(task.getT_OwnerID());//news发送给任务负责人
				news_toTMID.setN_Status(0);//news的初始状态，为未读取
				String t_nText = "【警告】任务【" + task.getT_Name() + "】即将到期，请及时完成！";
				news_toTMID.setN_Text(t_nText);
				news_toTMID.setN_ObjID(task.getT_ID());//news涉及到的任务
				newsService.addNews(news_toTMID);
				//设置task的t_5days
				task.setT_5days(1);//表明该项目已经检查过了，提醒之后，不再产生提示
				taskService.updateTask(task);
			}
		}
	}


}
