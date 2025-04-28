package com.enjoy.Spring.service.simple.impl;

import com.enjoy.Spring.config.aspects.annotations.JoinPointAfter;
import com.enjoy.Spring.config.aspects.annotations.JoinPointAfterReturning;
import com.enjoy.Spring.config.aspects.annotations.JoinPointAfterThrowing;
import com.enjoy.Spring.config.aspects.annotations.JoinPointAround;
import com.enjoy.Spring.config.aspects.annotations.JoinPointBefore;
import com.enjoy.Spring.service.simple.SimpleSleepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimpleSleepServiceImpl implements SimpleSleepService {

    @JoinPointBefore
    @Override
    public void oneSecondSleep() {
        log.info("### call one second sleep");
        this.sleep(1000);
    }

    @JoinPointAfter
    @Override
    public void twoSecondSleep() {
        log.info("### call two second sleep");
        this.sleep(2000);
    }

    @JoinPointAround
    @Override
    public void threeSecondSleep() {
        log.info("### call three second sleep");
        this.sleep(3000);
    }

    @JoinPointAfterReturning
    @Override
    public String oneSecondSleepAndReturnMessage() {
        log.info("### call one second sleep and return message");
        this.sleep(1000);
        return "call one second sleep and return message";
    }

    @JoinPointAfterThrowing
    @Override
    public String oneSecondSleepAndThrow() throws Exception {
        log.info("### call one second sleep and throw");
        this.sleep(1000);
        throw new RuntimeException("call one second sleep and throw");
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            log.error("##### Sleep Exception");
        }
    }
}
