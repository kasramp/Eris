package com.madadipouya.eris.service.mcp.exception;

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

public class UnexpectedProcessingException extends Exception {

  public UnexpectedProcessingException(String message) {
    super(message);
  }
}
