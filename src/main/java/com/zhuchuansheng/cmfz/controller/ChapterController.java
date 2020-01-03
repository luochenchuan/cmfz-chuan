package com.zhuchuansheng.cmfz.controller;

import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.entity.Album;
import com.zhuchuansheng.cmfz.entity.Chapter;
import com.zhuchuansheng.cmfz.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/24
 * @time: 15:45
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {


    @Autowired
    private ChapterService chapterService;


    /**
     *      分页查询
     *
     * @param page      当前页
     * @param rows      每一页显示数
     * @param albumId   章节所属的专辑ID
     * @return
     */
    @GetMapping("/queryAll")
    public Map<String, Object> queryAll(Integer page, Integer rows, String albumId){

        return chapterService.queryAllByPaging(page, rows, albumId);
    }


    /**
     *      添加章节数据
     *
     * @param chapter      数据
     * @return
     */
    @PostMapping("/addChapter")
    public Map<String, Object> addChapter(Chapter chapter){

        return chapterService.addChapter(chapter);
    }


    /**
     *      修改章节数据
     *
     * @param chapter       章节数据
     * @return
     */
    @RequestMapping("/updateChapter")
    public Result updateChapter(Chapter chapter, String oper){

        if("del".equals(oper)){

            Map<String, Object> map = chapterService.deleteChapter(chapter.getId(), chapter.getAlbumId());
            return new Result(200, map);
        }

        if("edit".equals(oper)) {

            Map<String, Object> map = chapterService.updateChapter(chapter);
            return new Result(200, map);
        }

        return new Result(500, "操作无效");
    }


    /**
     *     上传音频2
     *
     * @param src        上传音频文件
     * @param id         上传的音频所属专辑ID
     * @param session    作用域
     */
    @PostMapping("/uploadMusic")
    public void uploadMusic(MultipartFile src, String id, HttpSession session){

        //获取路径
        String realPath = session.getServletContext().getRealPath("/mp3");
        //设置名称
        String fileName = new Date().getTime() + "-" + src.getOriginalFilename();

        try {
            //上传文件
            File file = new File(realPath, fileName);
            src.transferTo(file);
            //获取文件的大小
            Long size = file.length() / 1024;
            //获取音频时长
            Encoder encoder = new Encoder();
            MultimediaInfo info = encoder.getInfo(file);
            long duration = info.getDuration();
            //设置参数
            Chapter chapter = new Chapter(id, null, null, String.valueOf(size), String.valueOf(duration), "/mp3/" + fileName, null);
            //更新数据
            chapterService.updateChapter(chapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *      音频下载
     *
     * @param src           下载路径
     * @param session       作用域
     * @param response      响应体
     */
    @RequestMapping("/downloadMusic")
    public void downloadMusic(String src, HttpSession session, HttpServletResponse response){

        //获取绝对路径
        String realPath = session.getServletContext().getRealPath(src);
        //创建文件
        File file = new File(realPath);
        FileInputStream fis = null;
        ServletOutputStream outputStream = null;
        try {
            //获取文件名
            int index = src.lastIndexOf("/");
            String fileName = src.substring(index + 1, src.length());
            //设置响应体
            response.setContentType("application/audio/mpeg");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //开始下载
            fis = new FileInputStream(file);
            outputStream = response.getOutputStream();

            byte[] buf = new byte[1024* 1024];
            int len;
            while((len = fis.read(buf)) != -1){
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     *      修改章节的状态
     *
     * @param id            章节ID
     * @param status        章节状态
     * @return
     */
    @PostMapping("/updateChapterStatus")
    public Result updateChapterStatus(String id, String status){

        Map<String, Object> map = chapterService.updateChapter(new Chapter(id, null, null, null, null, null, status));

        return new Result(200, map);
    }


}
