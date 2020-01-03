package com.zhuchuansheng.cmfz.dao;

import com.zhuchuansheng.cmfz.entity.Chapter;
import org.apache.ibatis.annotations.Param;
import sun.misc.Cache;

import java.util.List;

/**
 * @author zhuchuansheng
 * @date: 2019/12/24
 * @time: 15:20
 */
public interface ChapterDao {


    /**
     *      分页查询
     *
     * @param start     开始下标
     * @param rows      查询条数
     * @param albumId   章节所属专辑ID
     * @return
     */
    List<Chapter> selectAllByPaging(@Param("start") int start, @Param("rows") int rows, @Param("albumId") String albumId);


    /**
     *      根据ID查询总记录数
     *
     * @return
     */
    int selectCountById(@Param("albumId") String albumId);



    /**
     *      添加数据
     *
     * @param chapter   数据
     * @return
     */
    int insertChapter(Chapter chapter);


    /**
     *      修改数据
     *
     * @param chapter   数据
     * @return
     */
    int updateChapter(Chapter chapter);


    /**
     *      根据ID删除章节信息
     *
     * @param id        删除ID
     * @return
     */
    int deleteChapter(@Param("id") String id);

}
