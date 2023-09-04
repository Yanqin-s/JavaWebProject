package com.itms.Dao.impl;

import com.itms.Bean.News;
import com.itms.Dao.NewsDao;

import java.util.List;

public class NewsDaoImpl extends BaseDao implements NewsDao {
    @Override
    public int addNews(News news) {
        String sql = "insert into `news`(`n_Text`,`n_ToUID`,`n_Status`, `n_ObjID`) values(?,?,?,?)";
        return update(sql,news.getN_Text(),news.getN_ToUID(), news.getN_Status(),news.getN_ObjID());
    }

    @Override
    public int deleteNews(Integer n_ID) {
        String sql = "delete from `news` where `n_ID`=?";
        return update(sql,n_ID);
    }

    @Override
    public int updateNews(Integer n_ID) {
        String sql = "update `news` set `n_Status`=1 where `n_ID`=?";
        return update(sql,n_ID);
    }

    @Override
    public List<News> queryNewsByToUIDandStatus(String n_ToUID, Integer n_Status) {
        String sql = "select * from `news` where `n_ToUID`=? and `n_Status`=?";
        return queryForList(News.class,sql,n_ToUID, n_Status);
    }
}
