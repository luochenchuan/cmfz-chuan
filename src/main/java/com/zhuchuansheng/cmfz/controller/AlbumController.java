package com.zhuchuansheng.cmfz.controller;

import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.entity.Album;
import com.zhuchuansheng.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/24
 * @time: 15:42
 */
@RestController
@RequestMapping("/album")
public class AlbumController {


    @Autowired
    private AlbumService albumService;


    /**
     *      分页查询
     *
     * @param page      当前页
     * @param rows      每一页显示数
     * @return
     */
    @GetMapping("/queryAll")
    public Map<String, Object> queryAll(Integer page, Integer rows){

        return albumService.queryAllByPaging(page, rows);
    }


    /**
     *      修改专辑数据
     *
     * @param album     修改数据
     * @return
     */
    @RequestMapping("/updateAlbum")
    public Result updateAlbum(Album album, String oper){

        if("edit".equals(oper)) {

            album.setImg(null);
            Map<String, Object> map = albumService.updateAlbum(album);
            return new Result(200, map);
        }

        if("del".equals(oper)){

            Map<String, Object> map = albumService.deleteAlbum(album.getId());
            return new Result(200, map);
        }
        return new Result(500, "无效操作");
    }


    /**
     *      添加专辑
     *
     * @param album     专辑数据
     * @return
     */
    @PostMapping("/addAlbum")
    public Map<String, Object> addAlbum(Album album){

        Map<String, Object> map = albumService.addAlbum(album);
        return map;
    }


    /**
     * 上传封面
     */
    @PostMapping("/updateImage")
    public void uploadImageAndUpdateInfo(MultipartFile img, String id, HttpSession session){

        albumService.uploadImageAndUpdateInfo(img, id, session);
    }


    /**
     *      查询全部的专辑数据
     *
     * @return
     */
    @PostMapping("/queryAllAlbum")
    public List<Album> queryAll(){

        return albumService.queryAllAlbum();
    }


}
