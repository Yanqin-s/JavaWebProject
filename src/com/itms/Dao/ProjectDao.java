package com.itms.Dao;

import com.itms.Bean.Project;

import java.util.List;

public interface ProjectDao {
	
	/**
	 * 根据项目名字查询项目信息
	 * @param projectName
	 * @return 如果返回null，说明没有这个项目
	 */
	public Project queryProjectByProjectName(String projectName);
	
	/**
	 * 根据项目ID查询项目信息
	 * @param projectID
	 * @return
	 */
	public Project queryProjectByProjectID(String projectID);

	/**
	 * 根据用户ID和项目状态，返回对应的项目（新项目/在研/已完成）
	 * @param u_ID,p_Status
	 * @return 返回该用户所拥有的的项目列表
	 */
	public List<Project> queryProjectByP_MID(String u_ID, Integer p_Status);
	
	/**
	 * 修改项目（修改状态，预算，deadLine）
	 * @param project
	 * @return 修改成功，返回1
	 */
	public int updateProject(Project project);

	/**
	 * 查询某成员参与的项目
	 * @param u_ID
	 * @return
	 */
	public List<Project> queryProjectByMemberID(String u_ID);

//	/**
//	 * 查看用户在研项目中
//	 * @param p_MID
//	 * @param p_Status
//	 * @param p_5days
//	 * @return
//	 */
//	public List<Project> queryProjectByMIDAndStatusAnd5days(String p_MID, Integer p_Status, Integer p_5days);

}
