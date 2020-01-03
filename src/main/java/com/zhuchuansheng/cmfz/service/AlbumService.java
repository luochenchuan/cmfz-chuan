package com.zhuchuansheng.cmfz.service;

import com.zhuchuansheng.cmfz.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/24
 * @time: 15:27
 */
public interface AlbumService {


    /**
     *      分页查询专辑数据
     *
     * @param page          当前页
     * @param pageNum       每一页显示数
     * @return
     */
    Map<String, Object> queryAllByPaging(Integer page, Integer pageNum);


    /**
     *      根据ID查询专辑信息
     *
     * @param id
     * @return
     */
    Album queryAlbumById(String id);

    /**
     *      添加专辑数据
     *
     * @param album     专辑数据
     * @return
     */
    Map<String, Object> addAlbum(Album album);


    /**
     *      修改专辑数据
     *
     * @param album     专辑数据
     * @return
     */
    Map<String, Object> updateAlbum(Album album);


    /**
     *      查询全部的专辑数据
     *
     * @return
     */
    List<Album> queryAllAlbum();


    /**
     *      根据ID删除专辑
     *
     * @param id        删除的ID
     * @return
     */
    Map<String, Object> deleteAlbum(String id);


    Map<String, Object> uploadImageAndUpdateInfo(MultipartFile img, String id, HttpSession session);
}
