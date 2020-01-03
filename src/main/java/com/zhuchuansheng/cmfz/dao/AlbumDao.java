package com.zhuchuansheng.cmfz.dao;

import com.zhuchuansheng.cmfz.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhuchuansheng
 * @date: 2019/12/24
 * @time: 15:10
 */
public interface AlbumDao {

    /**
     *      分页查询数据
     *
     * @param start     开始下标
     * @param rows      查询条数
     * @return
     */
    List<Album> selectAllByPaging(@Param("start") int start, @Param("rows") int rows);


    /**
     *      根据ID查询总记录数
     *
     * @return
     */
    int selectCountById();


    /**
     *      添加专辑
     *
     * @param album     专辑信息
     * @return
     */
    int insertAlbum(Album album);


    /**
     *      更新专辑数据
     *
     * @param album     更新的数据
     * @return
     */
    int updateAlbum(Album album);


    /**
     *      查询全部的专辑数据
     *
     * @return
     */
    List<Album> selectAllAlbum();



    /**
     *      根据ID查询数据
     *
     * @param id        查询ID
     * @return
     */
    Album selectAlbumById(@Param("id") String id);


    /**
     *      根据ID删除专辑
     *
     * @param id    删除的ID
     * @return
     */
    int deleteAlbum(@Param("id") String id);
}


