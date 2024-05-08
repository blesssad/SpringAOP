package com.openschool.homework1.aspect;

import com.openschool.homework1.service.TrackTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class TrackTimeAspect {
    private final TrackTimeService trackTimeService;
    @Pointcut("@annotation(com.openschool.homework1.annotation.TrackTime)")
    public void trackTimePointcut() {}

    @Around("trackTimePointcut()")
    public Object trackTimePointcutRunner(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] methodArgs = joinPoint.getArgs();

        log.info("Выполнение метода {} с аргументами {}", methodName, methodArgs);

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        log.info("Метод {} выполнился за {} мс с результатом {}", methodName, endTime - startTime, result);
        trackTimeService.saveMethod(methodName, className, endTime - startTime);

        return result;
    }
}
