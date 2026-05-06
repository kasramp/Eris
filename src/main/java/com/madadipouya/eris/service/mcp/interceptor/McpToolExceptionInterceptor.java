package com.madadipouya.eris.service.mcp.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class McpToolExceptionInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(McpToolExceptionInterceptor.class);

    @Around("@annotation(org.springaicommunity.mcp.annotation.McpTool)")
    public Object handle(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            return proceedingJoinPoint.proceed();
        } catch (IllegalArgumentException illegalArgumentException) {
            throw illegalArgumentException;
        } catch (Exception exception) {
            logger.error("MCP tool {} failed", proceedingJoinPoint.getSignature().toShortString(), exception);
            // TODO - throw custom exception
            throw new RuntimeException("Unable to process the request due to an internal error");
        }
    }
}