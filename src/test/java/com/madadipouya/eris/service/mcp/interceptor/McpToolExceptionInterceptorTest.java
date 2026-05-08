package com.madadipouya.eris.service.mcp.interceptor;

import com.madadipouya.eris.service.mcp.exception.InvalidArgumentException;
import com.madadipouya.eris.service.mcp.exception.UnexpectedProcessingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

@ExtendWith(MockitoExtension.class)
class McpToolExceptionInterceptorTest {

  @InjectMocks
  private McpToolExceptionInterceptor mcpToolInterceptor;

  @Test
  void testInterceptCascadesInvalidArgumentException() throws Throwable {
    ProceedingJoinPoint proceedingJoinPoint = mock(ProceedingJoinPoint.class);
    when(proceedingJoinPoint.proceed()).thenThrow(new InvalidArgumentException("Invalid latitude or longitude"));

    assertThrows(InvalidArgumentException.class, () -> mcpToolInterceptor.intercept(proceedingJoinPoint));
  }

  @Test
  void testInterceptWrapsAnyExceptionInUnexpectedProcessingException() throws Throwable {
    ProceedingJoinPoint proceedingJoinPoint = mock(ProceedingJoinPoint.class);
    Signature interceptorSignature = mock(Signature.class);
    when(proceedingJoinPoint.getSignature()).thenReturn(interceptorSignature);
    when(interceptorSignature.toShortString()).thenReturn("Signature");
    when(proceedingJoinPoint.proceed()).thenThrow(new RuntimeException("Random exception"));

    assertThrows(UnexpectedProcessingException.class, () -> mcpToolInterceptor.intercept(proceedingJoinPoint));
  }
}