package com.zhuchuansheng.cmfz.controller.front;

import com.zhuchuansheng.cmfz.entity.Album;
import com.zhuchuansheng.cmfz.entity.Chapter;
import com.zhuchuansheng.cmfz.service.AlbumService;
import com.zhuchuansheng.cmfz.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/31
 * @time: 16:13
 */
@RestController
@RequestMapping("/detail")
public class FrontAlbumController {


    @Autowired
    private AlbumService albumService;

    @Autowired
    private ChapterService chapterService;


    /**
     *      专辑查询
     *
     * @param id        专辑ID
     * @param uid       用户ID
     */
    @RequestMapping("/wen")
    public Map<String, Object> wen(String id, String uid){

        Map<String, Object> result = new HashMap<>();

        //根据ID查询数据
        Album album = albumService.queryAlbumById(id);
        HashMap<Object, Object> albumHashMap = new HashMap<>();
        albumHashMap.put("thumbnail", album.getImg());
        albumHashMap.put("title", album.getTitle());
        albumHashMap.put("score", album.getScore());
        albumHashMap.put("author", album.getAuthor());
        albumHashMap.put("broadcast", album.getBroadcaster());
        albumHashMap.put("set_count", album.getCount());
        albumHashMap.put("brief", album.getBrief());
        result.put("introduction", albumHashMap);

        //查询数据
        Map<String, Object> map = chapterService.queryAllByPaging(1, 3, id);
        List<Chapter> rows = (List<Chapter>) map.get("rows");
        //存储章节
        List<Object> list = new ArrayList<>();
        for (Chapter row : rows) {
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("title", row.getTitle());
            hashMap.put("download_url", row.getSrc());
            hashMap.put("size", row.getSize());
            hashMap.put("duration", row.getDuration());
            list.add(hashMap);
        }
        result.put("list", list);

        return result;
    }


}
