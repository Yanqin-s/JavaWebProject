package com.itms.service;

import com.itms.Bean.News;

import java.util.List;

public interface NewsService {

    /**
     * 查询某用户的新消息
     * @param u_ToUID
     * @param n_Status
     * @return
     */
    public List<News> queryNewsByToUIDandStatus(String u_ToUID, Integer n_Status);

    /**
     * 添加消息
     * @param news
     */
    public void addNews(News news);

    /**
     * 用户删除消息
     * @param n_ID
     */
    public void deleteNews(Integer n_ID);

    /**
     * 用户修改消息状态，已读未读
     * @param n_ID
     */
    public void updateNews(Integer n_ID);
}
