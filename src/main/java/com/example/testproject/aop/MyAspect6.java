package com.example.testproject.aop;

import com.example.testproject.mapper.OperLogMapper;
import com.example.testproject.model.OperLog;
import com.example.testproject.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class MyAspect6 {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperLogMapper operLogMapper;

    @Pointcut("@annotation(com.example.testproject.aop.MyLog)")
    //@Pointcut("execution(public void com.example.testproject.service.imp.DeptServiceImp.deleteById(java.lang.Integer))")
    private void pt(){};

    /*
    @Before("pt()")
    public void before(JoinPoint joinPoint) {
        log.info("MyAspect before....");
        String methodName = joinPoint.getSignature().getName();//获得类名
    }*/
    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("MyAspect around...");
        //Object proceed = joinPoint.proceed();
        //记录操作日志
        System.out.println("userId___________________:");

        //操作人的id
        String jwt = request.getHeader("token");
        Claims claim = JwtUtils.parseJwt(jwt);
        Integer userId = (Integer) claim.get("id");
        log.info("MyAspect around...{}", claim);

        System.out.println("userId___________________:"+userId);
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //操作类名
        String className = joinPoint.getTarget().getClass().getName();
        //操作方法
        String methodName = joinPoint.getSignature().getName();
        //操作参数
        Object[] args = joinPoint.getArgs();
        String params = Arrays.toString(args);
        //调用目标方法运行
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        String returnValue = JSONObject.valueToString(result);
        long end = System.currentTimeMillis();
        Long costTime = end - begin;

        OperLog operLog = new OperLog(null,userId,operateTime,className,methodName,params,returnValue,costTime);
        operLogMapper.insert(operLog);
        return result;

    }


}
