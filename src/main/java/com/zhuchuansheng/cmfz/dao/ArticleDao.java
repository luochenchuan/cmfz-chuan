package com.zhuchuansheng.cmfz.dao;

import com.zhuchuansheng.cmfz.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {

    int deleteByPrimaryKey(String id);

    int insert(Article record);


    /**
     *      添加文章信息
     *
     * @param article       文章
     * @return
     */
    int insertSelective(Article article);


    Article selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);


    /**
     *      分页查询
     *
     * @param start     开始下标
     * @param rows      每一页显示数
     * @return
     */
    List<Article> selectAllByPaging(@Param("start") Integer start, @Param("rows") Integer rows);

    /**
     *      分页查询自己上师的文章
     *
     * @param start     开始下标
     * @param rows      每一页显示数
     * @return
     */
    List<Article> selectByPaging(@Param("start") Integer start, @Param("rows") Integer rows, @Param("guruId") String guruId);

    /**
     *      根据ID查询总记录数
     *
     * @return
     */
    int selectCountById();


    /**
     *        删除文章数据
     *
     * @param ids       ID集合
     * @return
     */
    int deleteArticle(String[] ids);

}