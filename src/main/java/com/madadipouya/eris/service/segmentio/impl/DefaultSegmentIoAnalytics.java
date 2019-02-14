package com.madadipouya.eris.service.segmentio.impl;

import com.madadipouya.eris.service.segmentio.SegmentIoAnalytics;
import com.madadipouya.eris.util.PropertyUtils;
import com.segment.analytics.Analytics;
import com.segment.analytics.messages.TrackMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

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
* © 2017-2019 Kasra Madadipouya <kasra@madadipouya.com>
*/

@Service("segmentIoAnalytics")
public class DefaultSegmentIoAnalytics implements SegmentIoAnalytics {

    Analytics analytics;

    private final PropertyUtils propertyUtils;

    public DefaultSegmentIoAnalytics(PropertyUtils propertyUtils) {
        this.propertyUtils = propertyUtils;
    }

    @PostConstruct
    public void afterPropertiesSet() {
        analytics = Analytics.builder(propertyUtils.getSegmentIoWriteApiKey()).build();
    }

    @Override
    public void fireEvent(EventType eventType, String id, Map<String, String> data) {
        analytics.enqueue(TrackMessage.builder(eventType.toString())
                .userId(id)
                .properties(data)
        );
    }
}
