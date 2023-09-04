package com.itms.Dao.impl;

import com.itms.Bean.StatisticTNumOfMember;
import com.itms.Bean.Task;
import com.itms.Dao.TaskDao;

import java.util.List;

public class TaskDaoImpl extends BaseDao implements TaskDao {
    @Override
    public int addTask(Task task) {
        String sql = "insert into `task`(`t_ID`,`t_Name`,`t_Description`,`t_PreTID`," +
                "`t_PostTID`,`t_StartDate`,`t_DeadLine`,`t_Duration`,`t_Budget`,`t_AC`,`t_Status`," +
                "`t_Evaluation`,`t_Wendang`,`t_PID`,`t_OwnerID`,`t_Period`,`t_AEndDate`) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return update(sql, task.getT_ID(), task.getT_Name(), task.getT_Description(), task.getT_PreTID(),
                task.getT_PostTID(), task.getT_StartDate(), task.getT_DeadLine(),
                task.getT_Duration(), task.getT_Budget(), task.getT_AC(), task.getT_Status(),
                task.getT_Evaluation(),task.getT_Wendang(),task.getT_PID(),task.getT_OwnerID(),
                task.getT_Period(),task.getT_AEndDate());
    }

    @Override
    public int delete(String T_ID) {
        String sql = "delete from `task` where t_ID=?";
        return update(sql,T_ID);
    }

    @Override
    public int update(Task task) {
//
        String sql = "update task set" +
                "`t_preTID` = ?, " +
                "`t_postTID` = ?," +
                "`t_StartDate` = ?, " +
                "`t_Duration` = ?," +
                "`t_AC` = ?, " +
                "`t_Status` = ?, " +
                "`t_Evaluation` = ?, " +
                "`t_Wendang` = ?, " +
                "`t_5days`=? " +
                "where t_ID = ?";
        return update(sql,
                task.getT_PreTID(),
                task.getT_PostTID(),
                task.getT_StartDate(),
                task.getT_Duration(),
                task.getT_AC(),
                task.getT_Status(),
                task.getT_Evaluation(),
                task.getT_Wendang(),
                task.getT_5days(),
                task.getT_ID());
    }




    @Override
    public Task queryTaskByTaskID(String T_ID) {
//        String sql = "select * from task where t_ID=?";
        String sql = "select a.*, p.p_Name as t_PName from " +
                "(select t.*, u.u_Name as t_OwnerName from task t join user u on t.t_OwnerID=u.u_ID) a join `project` p " +
                "on a.t_PID=p.p_ID " +
                "where a.t_ID = ?";

        return queryForOne(Task.class, sql, T_ID);
    }



    @Override
    public List<Task> queryTaskByOwnerIDandStatus(String T_OwnerID, Integer T_Status) {
//        String sql = "select * from task where t_OwnerID=? and t_Status=?";

          String sql = "select a.*, u.u_Name as t_OwnerName from " +
                  "(select t.*, p.p_Name as t_PName from `task` t join `project` p on t.t_PID=p.p_ID) a join `user` u " +
                  "on a.t_OwnerID=u.u_ID where t_OwnerID=? and t_Status=? order by `t_DeadLine`";
//        String sql = "select t.*, u.u_Name as t_OwnerName from " +
//                "task t join user u on t.t_OwnerID=u.u_Name where t_OwnerID=? and t_Status=? " +
//                "order by `t_DeadLine`";
        return queryForList(Task.class,sql,T_OwnerID, T_Status);
    }

    @Override
    public List<Task> queryTaskByofPIDandStatus(String T_of_PID, Integer T_Status) {
//        String sql = "select * from task where t_PID = ? and t_Status = ?";
        String sql = "select t.*, u.u_Name as t_OwnerName from task t join user u on " +
                "t.t_OwnerID=u.u_ID and t.t_PID=? and t.t_Status=? order by `t_DeadLine`";
        return queryForList(Task.class, sql, T_of_PID,T_Status);
    }

    @Override
    public List<Task> queryTaskByPIDandPeriod(String t_PID, String t_Period) {
        String sql = "select * from task where `t_PID`=? and `t_Period`=? order by `t_DeadLine`";
        return queryForList(Task.class, sql, t_PID,t_Period);
    }

    @Override
    public List<Task> queryTaskByPID(String t_PID) {
        String sql = "select a.*, u.u_Name as `t_OwnerName` from `user` u join (" +
                "select * from `task` where `t_PID`=?) a on " +
                "u.u_ID=a.t_OwnerID;";
        return queryForList(Task.class,sql,t_PID);
    }

    @Override
    public List<Task> queryTaskForNewsByPID(String t_PID) {
        String sql = "select * from `task` where `t_PID`=? and `t_Status`=1 and `t_5days`=0";
        return queryForList(Task.class,sql, t_PID);
    }

    @Override
    public List<Task> queryTaskForNewsByOwnerID(String t_OwnerID) {
        String sql = "select * from `task` where `t_OwnerID`=? and `t_Status`=1 and `t_5days`=0";
        return queryForList(Task.class, sql, t_OwnerID);
    }

    @Override
    public List<StatisticTNumOfMember> queryByTPID(String t_PID) {
//          String sql="select `taskNum` ,`t_OwnerID`, `u_Name` as `t_OwnerName` from `user` u join (" +
//                " select count(`t_ID`) as `taskNum`, `t_OwnerID` from `task` where `t_PID`= ? group by `t_OwnerID`) a on u.u_ID=a.t_OwnerID"
//        String sql = "select distinct a.taskNum ,a.`t_OwnerID`, u.`u_Name` as `t_OwnerName` , ifnull(b.doneTaskNum,0) as `doneTaskNum` , ifnull(`doneTaskNum`/a.taskNum, 0) as `fill_PC` " +
//                "from `user` u \n" +
//                "join (\n" +
//                "\tselect count(`t_ID`) as `taskNum`, `t_OwnerID` from `task` \n" +
//                "\twhere `t_PID`= ? group by `t_OwnerID`\n" +
//                ") a \n" +
//                "on u.u_ID=a.t_OwnerID\n" +
//                "left join (\n" +
//                "\tselect count(`t_ID`) as `doneTaskNum`, `t_OwnerID` from `task` \n" +
//                "    where `t_PID` = ? and `t_Status`=2 group by `t_OwnerID`\n" +
//                ") b\n" +
//                "on a.t_OwnerID=b.t_OwnerID " +
//                "order by `fill_PC`;";
        String sql = "select distinct a.taskNum ,a.`t_OwnerID`, u.`u_Name` as `t_OwnerName` , ifnull(b.doneTaskNum,0) as `doneTaskNum` , ifnull(`doneTaskNum`/a.taskNum, 0) as `fill_PC` " +
                "from `user` u \n" +
                "join (\n" +
                "\tselect count(`t_ID`) as `taskNum`, `t_OwnerID` from `task` \n" +
                "\twhere `t_PID`= ? group by `t_OwnerID`\n" +
                ") a \n" +
                "on u.u_ID=a.t_OwnerID\n" +
                "left join (\n" +
                "\tselect count(`t_ID`) as `doneTaskNum`, `t_OwnerID` from `task` \n" +
                "    where `t_PID` = ? and `t_Status`=2 group by `t_OwnerID`\n" +
                ") b\n" +
                "on a.t_OwnerID=b.t_OwnerID " +
                "order by `taskNum` ;";
        return queryForList(StatisticTNumOfMember.class, sql,t_PID, t_PID);
    }
}