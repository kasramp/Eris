package com.madadipouya.eris.service.segmentio;

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
* © 2017 Kasra Madadipouya <kasra@madadipouya.com>
*/

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.contains;

public interface SegmentIoAnalytics {

    enum EventType {
        CURRENT,
        CURRENT_BY_IP;

        public static EventType getEventType(String event) {
            return contains(event, "ByIp") ? CURRENT_BY_IP : CURRENT;
        }
    }

    void fireEvent(EventType eventType, String id, Map<String, String> data);
}
