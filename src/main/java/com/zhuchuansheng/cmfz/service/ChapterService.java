package com.zhuchuansheng.cmfz.service;

import com.zhuchuansheng.cmfz.entity.Chapter;

import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/24
 * @time: 15:36
 */
public interface ChapterService {


    /**
     *      分页查询
     *
     * @param page          当前页
     * @param pageNum       每一页显示数
     * @param albumId       章节所属的专辑ID
     * @return
     */
    Map<String, Object> queryAllByPaging(Integer page, Integer pageNum, String albumId);


    /**
     *      添加章节数据
     *
     * @param chapter   数据
     * @return
     */
    Map<String, Object> addChapter(Chapter chapter);


    /**
     *      修改章节数据
     *
     * @param chapter   章节数据
     * @return
     */
    Map<String, Object> updateChapter(Chapter chapter);


    /**
     *      根据ID删除章节数据
     *
     * @param id        删除ID
     * @return
     */
    Map<String, Object> deleteChapter(String id, String albumId);
}
