package com.planE.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduleTest {
    /**
     * 해당 메서드 로직이 끝나는 시간 기준, milliseconds 간격으로 실행
     * 이전 작업이 완료될 때까지 대기
     */
//    @Scheduled(fixedDelay = 10000)
//    public void scheduleFixedDelayTask() throws InterruptedException {
//        log.info("Fixed delay task - {}", System.currentTimeMillis() / 1000);
////        Thread.sleep(5000);
//    }

    /**
     * 해당 메서드 로직이 시작하는 시간 기준, milliseconds 간격으로 실행
     * 이전 작업이 완료될 때까지 다음 작업이 진행되지 않음
     */
//    @Async // 병렬로 Scheduler 를 사용할 경우 @Async 추가
//    @Scheduled(fixedRate = 10000)
//    public void scheduleFixedRateTask() throws InterruptedException {
//        log.info("Fixed rate task - {}", System.currentTimeMillis() / 1000);
////        Thread.sleep(5000);
//    }

    /**
     * 설정된 initialDelay 시간(milliseconds) 후부터 fixedDelay 시간(milliseconds) 간격으로 실행
     */
//    @Scheduled(fixedDelay = 10000, initialDelay = 50000)
//    public void scheduleFixedRateWithInitialDelayTask() {
//        long now = System.currentTimeMillis() / 1000;
//        log.info("Fixed rate task with one second initial delay -{}", now);
//    }

    /**
     * Cron 표현식을 사용한 작업 예약
     * 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7)
     *
     */
    //@Scheduled(cron = "*/30 * * * * *")
    public void scheduleTaskUsingCronExpression() {
        long now = System.currentTimeMillis() / 1000;
        log.info("----- 30초 마다 실행되는 배치 예시입니다.-----");
        log.info("schedule tasks using cron jobs - {}", now);
        log.info("Current Thread : {}", Thread.currentThread().getName());
        log.info("----------------------------------------");
    }
}
