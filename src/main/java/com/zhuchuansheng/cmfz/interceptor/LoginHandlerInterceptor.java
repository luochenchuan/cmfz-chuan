package com.zhuchuansheng.cmfz.interceptor;

import com.zhuchuansheng.cmfz.entity.Admin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zhuchuansheng
 * @date: 2019/12/23
 * @time: 8:45
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     *      请求之前执行
     *
     * @param request       请求对象
     * @param response      响应对象
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
        System.out.println("===="+loginAdmin);

        String requestURI = request.getRequestURI();
        System.out.println("uri:"+requestURI);

        return true;
    }

    /**
     *      请求之后执行
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     *      整个请求结束后的执行
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }





}
