package com.itms.Dao;

import com.itms.Bean.StatisticTNumOfMember;
import com.itms.Bean.Task;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface TaskDao {

    /**
     * 添加任务
     * @param task：已经set过的任务实体
     * @return
     */
    public int addTask(Task task);

    /**
     * 根据任务ID删除任务
     * @param T_ID
     * @return
     */
    public int delete(String T_ID);

    /**
     * 更新task，常用于更新任务的状态，成本，前置任务ID等等
     * @param task
     * @return
     */
    public int update(Task task);



    /**
     * 根据任务ID查询任务
     * @param T_ID
     * @return
     */
    public Task queryTaskByTaskID(String T_ID);

    /**
     * 根据任务的负责人ID和任务状态查询任务。
     * 若任务状态为0，则显示该成员的未领取任务；若任务状态为1，则显示该成员的在研任务；若状态为2，则显示已完成任务
     * @param T_OwnerID
     * @param T_Status
     * @return
     */
    public List<Task> queryTaskByOwnerIDandStatus(String T_OwnerID, Integer T_Status);

    /**
     * 根据任务所属项目ID和任务的状态查询任务，即：查询某个项目下的待领取/在研/已完成任务
     * @param T_of_PID
     * @param T_Status
     * @return
     */
    public List<Task> queryTaskByofPIDandStatus(String T_of_PID, Integer T_Status);

    /**
     * 根据所属项目ID和任务的阶段查询任务，主要用于生成任务ID
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
     * 查询某项目下在研任务中没有被提醒的任务
     * @param t_PID
     * @return
     */
    public List<Task> queryTaskForNewsByPID(String t_PID);

    /**
     * 查询任务负责人的所有未被提醒的任务
     * @param t_OwnerID
     * @return
     */
    public List<Task> queryTaskForNewsByOwnerID(String t_OwnerID);

    /**
     * 查询某个项目下，每个成员的任务数量
     * @param t_PID
     * @return
     */
    public List<StatisticTNumOfMember> queryByTPID(String t_PID);
}
