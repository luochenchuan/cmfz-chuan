package com.zhuchuansheng.cmfz.service;

import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.entity.Article;

import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/25
 * @time: 17:37
 */
public interface ArticleService {


    /**
     *      分页查询
     *
     * @param page      当前页
     * @param rows      每一页显示数
     * @return
     */
    Map<String, Object> queryAllByPaging(Integer page, Integer rows);


    /**
     *      分页查询自己上师的文章
     *
     * @param page      当前页
     * @param rows      每一页显示数
     * @return
     */
    Map<String, Object> queryByPaging(Integer page, Integer rows, String guruId);

    /**
     *      添加文章信息
     *
     * @param article  文章
     * @return
     */
    Map<String, Object> addArticleInfo(Article article);


    /**
     *      更新文章数据
     *
     * @param article       文章
     * @return
     */
    Map<String, Object> updateArticleInfo(Article article);


    /**
     *      根据ID删除
     *
     * @param ids       删除ID的集合
     * @return
     */
    Map<String, Object> deleteArticle(String[] ids);


    /**
     *      修改文章状态
     *
     * @param id            修改ID
     * @param status        状态
     * @return
     */
    Map<String, Object> updateArticleStatus(String id, String status);
}
