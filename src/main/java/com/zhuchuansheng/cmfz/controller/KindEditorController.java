package com.zhuchuansheng.cmfz.controller;

import com.zhuchuansheng.cmfz.service.KindEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/26
 * @time: 14:14
 */
@RestController
@RequestMapping("/kindEditor")
public class KindEditorController {


    @Autowired
    private KindEditorService kindEditorService;


    /**
     *      图片上传
     *
     * @param img           上传的图片
     * @param session
     * @param request
     * @return
     */
    @PostMapping("/uploadImage")
    public Map<String, Object> kindEditorUploadImage(MultipartFile img, HttpSession session, HttpServletRequest request){

       return kindEditorService.updateImage(img, session, request);
    }




    @GetMapping("/imageSpace")
    public Map<String, Object> kindEditorImageSpace(HttpServletRequest request){

        return kindEditorService.imageSpace(request);
    }


}
