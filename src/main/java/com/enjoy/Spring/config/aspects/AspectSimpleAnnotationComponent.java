package com.enjoy.Spring.config.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectSimpleAnnotationComponent {

    private static final String AOP_TAG = "[AOP]";

    @Before("@annotation(com.enjoy.Spring.config.aspects.annotations.JoinPointBefore)")
    public void joinPointBefore() {
        log.info("### {} Before", AOP_TAG);
    }

    @After("@annotation(com.enjoy.Spring.config.aspects.annotations.JoinPointAfter)")
    public void joinPointAfter() {
        log.info("### {} After", AOP_TAG);
    }

    @AfterReturning(value = "@annotation(com.enjoy.Spring.config.aspects.annotations.JoinPointAfterReturning)",
                    returning = "message")
    public void joinPointAfterReturning(String message) {
        log.info("### {} After Returning, then Return: {}", AOP_TAG, message);
    }

    @AfterThrowing(value = "@annotation(com.enjoy.Spring.config.aspects.annotations.JoinPointAfterThrowing)",
        throwing = "e")
    public void joinPointAfterThrowing(RuntimeException e) {
        log.info("### {} After Throwing, then Throw: {}", AOP_TAG, e.getMessage());
    }

    @Around("@annotation(com.enjoy.Spring.config.aspects.annotations.JoinPointAround)")
    public Object joinPointAround(ProceedingJoinPoint joinPoint) {
        log.info("### {} Around", AOP_TAG);

        log.info("### --> {} Before", AOP_TAG);
        Object proceed = null;

        try {
            proceed = joinPoint.proceed();
        }
        catch (Throwable e) {
            log.error("### {} Aspect Around Exception", AOP_TAG);
        }

        log.info("### --> {} After", AOP_TAG);

        return proceed;
    }
}
