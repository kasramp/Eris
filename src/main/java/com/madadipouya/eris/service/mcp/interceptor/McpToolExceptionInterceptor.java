package com.madadipouya.eris.service.mcp.interceptor;

import com.madadipouya.eris.service.mcp.exception.InvalidArgumentException;
import com.madadipouya.eris.service.mcp.exception.UnexpectedProcessingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * This file is part of Eris Weather API.
 *
 * Eris Weather API is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3
 * as published by the Free Software Foundation.
 *
 * Eris Weather API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.  <http://www.gnu.org/licenses/>
 *
 * Author(s):
 *
 * © 2026 Kasra Madadipouya <kasra@madadipouya.com>
 */

@Component
@Aspect
public class McpToolExceptionInterceptor {
  private static final Logger logger = LoggerFactory.getLogger(McpToolExceptionInterceptor.class);

  @Around("@annotation(org.springaicommunity.mcp.annotation.McpTool)")
  public Object intercept(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    try {
      return proceedingJoinPoint.proceed();
    } catch (InvalidArgumentException invalidArgumentException) {
      throw invalidArgumentException;
    } catch (Exception exception) {
      logger.error("MCP tool {} failed", proceedingJoinPoint.getSignature().toShortString(), exception);
      throw new UnexpectedProcessingException("Unable to process the request due to an internal error");
    }
  }
}