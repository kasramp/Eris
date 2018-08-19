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
* © 2017-2018 Kasra Madadipouya <kasra@madadipouya.com>
*/

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.contains;

/**
 * This service is responsible to send usage analytics of the {@link com.madadipouya.eris.service.weather.model.CurrentWeatherCondition}
 * API to @see <a href="segment.io">Segment.io</a>
* */
public interface SegmentIoAnalytics {

    enum EventType {
        CURRENT,
        CURRENT_BY_IP;
        public static EventType getEventType(String event) {
            return contains(event, "ByIp") ? CURRENT_BY_IP : CURRENT;
        }
    }

    /**
     * Sends an event to @see <a href="segment.io">Segment.io</a>
     * @param eventType Type of event. Value can be {@link EventType#CURRENT} or {@link EventType#CURRENT_BY_IP}
     * @param id Identifier in Segment platform, here is IP address
     * @param data The data that want to send
    * */
    void fireEvent(EventType eventType, String id, Map<String, String> data);
}
