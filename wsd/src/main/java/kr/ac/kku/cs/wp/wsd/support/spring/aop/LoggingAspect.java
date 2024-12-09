package kr.ac.kku.cs.wp.wsd.support.spring.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterThrowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // 모든 컨트롤러 메서드 호출 전 로그
    @Before("execution(* kr.ac.kku.cs.wp.wsd.user.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("컨트롤러 메서드 호출: {}", joinPoint.getSignature().toShortString());
    }

    // 컨트롤러 메서드 실행 중 예외 발생 로그
    @AfterThrowing(pointcut = "execution(* kr.ac.kku.cs.wp.wsd.user.controller.*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        logger.error("예외 발생 - 메서드: {}, 메시지: {}", joinPoint.getSignature().toShortString(), exception.getMessage());
    }
}