package com.zhuchuansheng.cmfz.dao;

import com.zhuchuansheng.cmfz.entity.Banner;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhuchuansheng
 * @date: 2019/12/23
 * @time: 15:44
 */
public interface BannerDao {


    /**
     *      分页查询
     *
     * @param start      当前下标
     * @param rows      查询数
     * @return
     */
    List<Banner> selectByPaging(@Param("start") Integer start, @Param("rows") Integer rows);


    /**
     *      通过ID查询全部的记录数
     *
     * @return
     */
    int selectCountById();


    /**
     *      保存轮播图的数据
     *
     * @param banner        轮播图数据
     * @return
     */
    int insertBanner(Banner banner);


    /**
     *      修改轮播图数据
     *
     * @param banner        轮播图数据
     * @return
     */
    int updateBannerById(Banner banner);


    /**
     *      批量删除轮播图
     *
     * @param list       id集合
     * @return
     */
    int deleteAll(@Param("list") List<String> list);


    /**
     *      批量插入
     *
     * @param banners       一个集合
     * @return
     */
    int insertByList(List<Banner> banners);

}
