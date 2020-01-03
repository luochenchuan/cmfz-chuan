package com.zhuchuansheng.cmfz.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhuchuansheng
 * @date: 2020/1/3
 * @time: 17:09
 */
@Component
@Aspect
public class AspectRedisCache {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     *      环绕通知
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("@annotation(com.zhuchuansheng.cmfz.annotation.AddCache)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //获取存储的key值
        String[] names = this.getNames(proceedingJoinPoint);

        //获取缓存中的hash集合对象
        HashOperations hash = redisTemplate.opsForHash();
        //判断缓存是否存在
        if(hash.hasKey(names[0], names[1])){
            System.out.println("获取缓存");
            //返回缓存
            return hash.get(names[0], names[1]);
        }

        for (String name : names) {

            System.out.println(name);
        }

        //执行目标方法
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println(proceed);
        //添加缓存
        hash.put(names[0], names[1], proceed);
        System.out.println("添加缓存");
        return proceed;
    }


    @After("@annotation(com.zhuchuansheng.cmfz.annotation.ClearCache)")
    public void After(JoinPoint joinPoint){

        //获取类名
        String className = joinPoint.getTarget().getClass().getName();
        //清楚缓存
        redisTemplate.delete(className);
        System.out.println("清除缓存");
    }



    /**
     *      获取key值
     *   names[0]   存储类名
     *   names[1]   存储方法名和参数
     *
     * @param proceedingJoinPoint
     * @return
     */
    private String[] getNames(ProceedingJoinPoint proceedingJoinPoint){

        //存储名字的数组
        String[] names = new String[2];
        System.out.println("name length: " + names.length);
        //获取具体的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        names[0] = className;

        //获取方法名
        StringBuffer param = new StringBuffer();
        String name = proceedingJoinPoint.getSignature().getName();
        param.append(name);
        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if(arg != null)
                param.append(arg);
        }
        names[1] = new String(param);

        return names;
    }



}
