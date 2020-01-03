package com.zhuchuansheng.cmfz.aop;

import com.zhuchuansheng.cmfz.service.EchartsService;
import io.goeasy.GoEasy;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhuchuansheng
 * @date: 2019/12/30
 * @time: 21:35
 */
@Component
@Aspect
public class AspectTUserInsert {


    @Autowired
    private EchartsService echartsService;

    /**
     *      切入点表达式
     *
     */
    @Pointcut(value = "execution(* com.zhuchuansheng.cmfz.service.TUserService.add*(..))")
    public void expression(){

    }


    /**
     *       后置通知
     *
     */
    @After("expression()")
    public void afterInsert(){

        //创建GoEast
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-00a40ddf77f9434c903e08e8ac73e386");

        //查询每一个月的消息
        List<Integer> integers = echartsService.queryMonthCountByRegister();

        //发布消息
        goEasy.publish("month-register", integers.toString());

    }

}
