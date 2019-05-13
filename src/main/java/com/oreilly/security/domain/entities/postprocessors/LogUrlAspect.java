package com.oreilly.security.domain.entities.postprocessors;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect
public class LogUrlAspect {

    @Pointcut("execution(* com.oreilly.security+.*(..))")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void httpServletRequest2() {
        System.out.println("AAAA");
    }


}
