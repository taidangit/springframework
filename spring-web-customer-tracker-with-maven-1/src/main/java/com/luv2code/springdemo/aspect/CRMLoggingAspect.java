package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	private static Logger LOGGER = LoggerFactory.getLogger(CRMLoggingAspect.class);

	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}

	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}

	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}

	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}

	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		String theMethod = theJoinPoint.getSignature().toShortString();
		LOGGER.info("=======>> in @Before: calling method: " + theMethod);

		Object[] args = theJoinPoint.getArgs();
		for (Object tempArg : args) {
			LOGGER.info("======>> argument: " + tempArg);
		}
	}

	@AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		String theMethod = theJoinPoint.getSignature().toShortString();
		LOGGER.info("=======>> in @AfterReturning: calling method: " + theMethod);

		LOGGER.info("=====>> result: " + theResult);
	}
}
