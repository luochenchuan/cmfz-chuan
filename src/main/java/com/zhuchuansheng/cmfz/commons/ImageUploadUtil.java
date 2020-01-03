package com.zhuchuansheng.cmfz.commons;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 *  文件上传工具类
 *
 * @author zhuchuansheng
 * @date: 2019/12/26
 * @time: 19:18
 */
public class ImageUploadUtil {


    /**
     *      图片上传
     *
     * @param session       HttpSession  对象
     * @param fileName      保存在文件
     * @param src           上传的文件
     * @param adviceImage   通知
     * @return
     */
    public static String uploadImage(HttpSession session,
                            String fileName, MultipartFile src, AdviceImage adviceImage){

        //获取图片保存的路径
        String realPath = session.getServletContext().getRealPath(fileName);
        //检查文件夹是否存在
        isExist(realPath);
        //获取文件的新名字
        String[] name = getName(src.getOriginalFilename());
        //文件上传
        try {
            if (adviceImage != null)
                adviceImage.beforeAdvice();

            src.transferTo(new File(realPath, name[0]));

            if(adviceImage != null)
                adviceImage.afterAdvice();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return name[0];
    }


    /**
     *        判断文件夹是否存在
     *
     * @param path      文件夹路径
     */
    private static void isExist(String path){

        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }


    /**
     *      获取图片的新名字和图片的后缀
     *
     * @param fileName
     * @return
     */
    private static String[] getName(String fileName){

        //获取图片的后缀
        String extension = FilenameUtils.getExtension(fileName);

        //重新组成名字
        String name = new Date().getTime() + "-" + fileName;
        String[] arr = {name, extension};

        return arr;
    }


}
