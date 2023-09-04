package com.itms.Dao;

import com.itms.Bean.News;
import com.itms.Dao.impl.BaseDao;

import java.util.List;

public interface NewsDao{

    /**
     * 添加消息
     * @param news
     * @return
     */
    public int addNews(News news);


    public int deleteNews(Integer n_ID);

    /**
     * 需要修改news的状态，从0-1
     * @param
     * @return
     */
    public int updateNews(Integer n_ID);

    public List<News> queryNewsByToUIDandStatus(String n_ToUID, Integer n_Status);


}
