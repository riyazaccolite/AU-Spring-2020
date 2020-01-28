package com.accolite.AU.SpringAssignment.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut(value = "execution(* com.accolite.AU.SpringAssignment.dao.UserDaoImpl.*(..))")
    public void daoClassMethods() { }

    @Before("daoClassMethods()")
    public void before(JoinPoint joinPoint) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        logger.info("<ASPECT LOG> Called " + joinPoint.getSignature().getName() + " " + dateFormat.format(date));
    }

    @After("daoClassMethods()")
    public void after(JoinPoint joinPoint) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        logger.info("<ASPECT LOG> Completed " + joinPoint.getSignature().getName() + " " + dateFormat.format(date));
    }

    @AfterThrowing("daoClassMethods()")
    public void afterThrowing(JoinPoint joinPoint) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        logger.info("<ASPECT LOG> Completed with Exception " + joinPoint.getSignature().getName() + " " + dateFormat.format(date));
    }

    @AfterReturning("daoClassMethods()")
    public void afterReturning(JoinPoint joinPoint) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        logger.info("<ASPECT LOG> Completed successfully " + joinPoint.getSignature().getName() + " " + dateFormat.format(date));
    }

    @Around("daoClassMethods()")
    public Object aroundDemo(ProceedingJoinPoint pjp) throws Throwable{
        logger.info("<ASPECT LOG> Logged before calling in around");
        Object returnedVal = pjp.proceed();
        logger.info("<ASPECT LOG> Logged after calling in around");
        return returnedVal;
    }
}
