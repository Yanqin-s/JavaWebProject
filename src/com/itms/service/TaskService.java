package com.itms.service;

import com.itms.Bean.StatisticTNumOfMember;
import com.itms.Bean.Task;

import java.util.List;

public interface TaskService {

    /**
     * 添加新任务，用于项目配置时，添加新任务
     * @param task
     */
    public void addTask(Task task);

    /**
     * 删除任务，项目经理配置项目时，删除任务
     * @param t_ID
     */
    public void deleteTask(String t_ID);

    /**
     * 任务负责人或项目经理在配置项目时修改任务
     * @param task
     */
    public void updateTask(Task task);

    /**
     * 任务负责人查看自己的待领取任务和在研任务
     * @param task
     * @return
     */
    public Task queryTaskByTaskID(String task);



    /**
     * 根据任务负责人的ID和任务的状态查询任务,查看自己在研的任务和新任务
     * @param t_OwnerID：任务负责人ID
     * @param t_Status:任务状态
     * @return 返回任务列表
     */
    public List<Task> queryTaskByOwnerIDandStatus(String t_OwnerID, Integer t_Status);

    /**
     * 根据所属项目的PID查询任务，常用于项目经理查看该项目下的任务状态（查看任务是否完成，还有多久完成）
     * @param t_PID：任务的项目ID
     * @param t_Status:任务状态
     * @return
     */
    public List<Task> queryTaskByPIDandStatus(String t_PID, Integer t_Status);

    /**
     * 根据所属项目PID和任务的阶段查询任务，常用于生成taskID
     * @param t_PID
     * @param t_Period
     * @return
     */
    public List<Task> queryTaskByPIDandPeriod(String t_PID, String t_Period);

    /**
     * 查询项目下的所有任务
     * @param t_PID
     * @return
     */
    public List<Task> queryTaskByPID(String t_PID);

    /**
     * 查询项目下所有没有被提醒的任在研任务
     * @param t_PID
     * @return
     */
    public List<Task> queryTaskForNewsByPID(String t_PID);

    /**
     * 查询该用户的所有未被提醒的在研任务
     * @param t_OwnerID
     * @return
     */
    public List<Task> queryTaskForNewsByOwnerID(String t_OwnerID);

    /**
     * 查询某个项目的每个成员的任务数量
     * @param t_PID
     * @return
     */
    public List<StatisticTNumOfMember> queryByTPID(String t_PID);

}

