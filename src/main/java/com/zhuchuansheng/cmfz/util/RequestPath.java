package com.zhuchuansheng.cmfz.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhuchuansheng
 * @date: 2019/12/29
 * @time: 8:20
 */
public class RequestPath {


    /**
     *      获取的绝对请求路径<br/>
     *          返回形式: http://localhost:80/xxx/
     *
     * @param request       请求对对象
     * @return
     */
    public static String getRequestPath(HttpServletRequest request){

        try {
            //获取请求协议
            String scheme = request.getScheme();
            //获取请求主机IP
            String localHost = InetAddress.getLocalHost().toString().split("/")[1];
            //获取请求端口
            int port = request.getServerPort();
            //获取请求根路径
            String contextPath = request.getContextPath();
            //返回路径
            return scheme + "://" + localHost + ":" + port + contextPath;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }



}
