package com.enjoy.Spring.service.simple;

public interface SimpleSleepService {

    void oneSecondSleep();
    void twoSecondSleep();
    void threeSecondSleep();

    String oneSecondSleepAndReturnMessage();
    String oneSecondSleepAndThrow() throws Exception;
}
