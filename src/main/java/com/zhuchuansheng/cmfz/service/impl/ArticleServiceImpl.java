package com.zhuchuansheng.cmfz.service.impl;

import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.dao.ArticleDao;
import com.zhuchuansheng.cmfz.entity.Article;
import com.zhuchuansheng.cmfz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author zhuchuansheng
 * @date: 2019/12/25
 * @time: 17:39
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryAllByPaging(Integer page, Integer rows) {

        if(page == null)
            page = 1;
        if(rows == null)
            rows = 3;

        //计算开始下标
        int start = (page - 1) * rows;
        List<Article> articles = articleDao.selectAllByPaging(start, rows);
        int count = articleDao.selectCountById();
        int total = count % rows == 0 ? count / rows : count / rows + 1;

        Map<String, Object> result = new HashMap<>();
        result.put("page", page);
        result.put("records", count);
        result.put("total", total);
        result.put("rows",  articles);

        return result;
    }


    @Override
    public Map<String, Object> addArticleInfo(Article article) {

        Map<String, Object> result = new HashMap<>();
        //设置ID
        String uuid = UUID.randomUUID().toString();
        article.setId(uuid);
        //设置上传时间
        article.setCreateDate(new Date());

        int i = articleDao.insertSelective(article);

        result.put("uuid", uuid);
        result.put("success", i);

        return result;
    }

    @Override
    public Map<String, Object> updateArticleInfo(Article article) {

        Map<String, Object> result = new HashMap<>();
        int i = articleDao.updateByPrimaryKeySelective(article);
        result.put("success", i);

        return result;
    }


    @Override
    public Map<String, Object> deleteArticle(String[] ids) {

        int i = articleDao.deleteArticle(ids);
        Map<String, Object> result = new HashMap<>();
        result.put("success", i);

        return result;
    }


    @Override
    public Map<String, Object> updateArticleStatus(String id, String status) {

        Map<String, Object> result = new HashMap<>();
        int i = articleDao.updateByPrimaryKeySelective(new Article(id, null, null, null, null, null, status, null));
        result.put("success", i);

        return result;
    }

    @Override
    public Map<String, Object> queryByPaging(Integer page, Integer rows, String guruId) {



        return null;
    }
}
