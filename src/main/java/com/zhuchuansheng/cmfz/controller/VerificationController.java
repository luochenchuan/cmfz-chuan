package com.zhuchuansheng.cmfz.controller;

import com.zhuchuansheng.cmfz.util.ValidateImageCodeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

/**
 * @author zhuchuansheng
 * @date: 2019/12/20
 * @time: 18:53
 */
@RestController
@RequestMapping("/code")
public class VerificationController {


    /**
     * 获取验证码图片
     *
     * @param response   响应对象
     */
    @GetMapping("/getCodeImage")
    public void getCodeImage(HttpServletResponse response, HttpSession session){

        //获取验证码字符
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        //存储验证码字符
        session.setAttribute("securityCode", securityCode);
        //创建图片
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
        //将图片写到页面中
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
