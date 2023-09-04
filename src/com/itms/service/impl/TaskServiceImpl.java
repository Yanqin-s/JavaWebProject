package com.itms.service.impl;

import com.itms.Bean.StatisticTNumOfMember;
import com.itms.Bean.Task;
import com.itms.Dao.TaskDao;
import com.itms.Dao.impl.TaskDaoImpl;
import com.itms.service.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {

    TaskDao taskDao = new TaskDaoImpl();

    @Override
    public void addTask(Task task) {
        taskDao.addTask(task);
    }

    @Override
    public void deleteTask(String t_ID) {
        taskDao.delete(t_ID);
    }

    @Override
    public void updateTask(Task task) {
        taskDao.update(task);
    }

    @Override
    public Task queryTaskByTaskID(String t_ID) {
        return taskDao.queryTaskByTaskID(t_ID);
    }

    @Override
    public List<Task> queryTaskByOwnerIDandStatus(String t_OwnerID, Integer t_Status) {
        return taskDao.queryTaskByOwnerIDandStatus(t_OwnerID,t_Status);
    }

    @Override
    public List<Task> queryTaskByPIDandStatus(String t_PID, Integer t_Status) {

        return taskDao.queryTaskByofPIDandStatus(t_PID, t_Status);
    }

    @Override
    public List<Task> queryTaskByPIDandPeriod(String t_PID, String t_Period) {
        return taskDao.queryTaskByPIDandPeriod(t_PID,t_Period);
    }

    @Override
    public List<Task> queryTaskByPID(String t_PID) {
        return taskDao.queryTaskByPID(t_PID);
    }

    @Override
    public List<Task> queryTaskForNewsByPID(String t_PID) {
        return taskDao.queryTaskForNewsByPID(t_PID);
    }

    @Override
    public List<Task> queryTaskForNewsByOwnerID(String t_OwnerID) {
        return taskDao.queryTaskForNewsByOwnerID(t_OwnerID);
    }

    public List<StatisticTNumOfMember> queryByTPID(String t_PID) {
        return taskDao.queryByTPID(t_PID);
    }
}
