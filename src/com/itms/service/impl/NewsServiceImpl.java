package com.itms.service.impl;

import com.itms.Bean.News;
import com.itms.Dao.NewsDao;
import com.itms.Dao.impl.NewsDaoImpl;
import com.itms.service.NewsService;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    private NewsDao newsDao = new NewsDaoImpl();
    @Override
    public List<News> queryNewsByToUIDandStatus(String n_ToUID, Integer n_Status) {
        return newsDao.queryNewsByToUIDandStatus(n_ToUID, n_Status);
    }

    @Override
    public void addNews(News news) {
        newsDao.addNews(news);
    }

    @Override
    public void deleteNews(Integer n_ID) {
        newsDao.deleteNews(n_ID);
    }

    @Override
    public void updateNews(Integer n_ID) {
        newsDao.updateNews(n_ID);
    }
}
