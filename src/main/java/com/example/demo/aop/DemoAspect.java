package com.example.demo.aop;

import com.example.demo.annotation.Authorize;
import com.example.demo.exception.AuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

@Component
@Aspect
public class DemoAspect {

    @Pointcut("execution(* com.example.demo.controller.HomeController.*(..)) && @annotation(com.example.demo.annotation.LogAspect)")
    public void loggerPointCut(){}

    @Before(value = "loggerPointCut()")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("-------------------Method name: "+joinPoint.getSignature().getName()+" -------------------");
        return joinPoint.proceed();
    }

    @Pointcut("execution(* com.example.demo.controller.HomeController.*(..)) && @annotation(com.example.demo.annotation.Authorize)")
    public void authorizePointCut(){}

    @Around(value = "authorizePointCut()")
    public Object authorizeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("------------------- "+joinPoint.getSignature().getName()+" -------------------");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Authorize annotation = methodSignature.getMethod().getAnnotation(Authorize.class);
        List<String> roles = Arrays.asList(annotation.roles());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String role = request.getHeader("role");

        if(!roles.contains(role))
            throw new AuthorizationException("Authorization Failed.");
        System.out.println("User has required role access.");
        return joinPoint.proceed();
    }
}
