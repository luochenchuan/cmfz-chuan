package com.zhuchuansheng.cmfz.controller;

import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.entity.Article;
import com.zhuchuansheng.cmfz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/25
 * @time: 18:08
 */
@RestController
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;


    /**
     *      分页查询
     *
     * @param page      当前页
     * @param rows      每一页显示数量
     * @return
     */
    @GetMapping("/queryAll")
    public Map<String, Object> queryByPaging(Integer page, Integer rows){

        return articleService.queryAllByPaging(page, rows);
    }


    /**
     *      添加文章信息
     *
     * @param article   文件信息
     * @return
     */
    @PostMapping("/addArticle")
    public Result addArticleInfo(Article article){

        Map<String, Object> map = articleService.addArticleInfo(article);

        return new Result(200, map);
    }


    /**
     *      修改文章数据
     *
     * @param article       文章
     * @return
     */
    @PostMapping("/updateArticle")
    public Result updateArticleInfo(Article article){

        Map<String, Object> map = articleService.updateArticleInfo(article);

        return new Result(200, map);
    }


    /**
     *      删除文章
     *
     * @param id        删除的ID集合
     * @return
     */
    @RequestMapping("/deleteArticle")
    public Result deleteArticle(String[] id){

        Map<String, Object> map = articleService.deleteArticle(id);

        return new Result(200, map);
    }


    /**
     *      修改文章状态
     *
     * @param id        ID
     * @param status    状态
     * @return
     */
    @PostMapping("/updateStatus")
    public Result updateStatus(String id, String status){

        Map<String, Object> map = articleService.updateArticleStatus(id, status);

        return new Result(200, map);
    }

}
