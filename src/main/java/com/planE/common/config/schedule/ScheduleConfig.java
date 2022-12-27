package com.planE.common.config.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * Spring boot 스케줄러를 이용한 배치 환경구성 소스
 * ---------- Thread pool 설정 ---------------------------------------------------------
 *   기본적으로 모든 @Scheduled 작업은 Spring에 의해 생성 된 한개의 스레드 풀에서 실행된다.
 *   그렇기 때문에 하나의 Scheduled이 돌고 있다면 그것이 다 끝나야 다음 Scheduled이 실행되는 문제가 있다.
 *   설정을 통해 Schedule에 대한 쓰래드 풀을 생성하고 그 쓰레드 풀을 사용하여 모든 스케줄 된 작업을 실행하도록 할 수 있다.
 *   -> 아래 소스에는 설정을 적용해두었음.
 *   -> 기본 설정 방법은 제일 하단 주석 처리 되어있음.
 * ------------------------------------------------------------------------------------
 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {
    private final int POOL_SIZE = 10;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("my-scheduled-task-pool-");
        threadPoolTaskScheduler.initialize();

        scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }
}

/*  기본 스케줄러 설정 예시
@Configuration
@EnableScheduling
public class ScheduleConfig{
}
 */