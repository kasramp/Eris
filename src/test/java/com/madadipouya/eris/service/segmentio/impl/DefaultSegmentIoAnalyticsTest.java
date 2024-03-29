package com.madadipouya.eris.service.segmentio.impl;

import com.madadipouya.eris.service.segmentio.SegmentIoAnalytics;
import com.madadipouya.eris.util.PropertyUtils;
import com.segment.analytics.Analytics;
import com.segment.analytics.messages.MessageBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.*;

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
* © 2017-2022 Kasra Madadipouya <kasra@madadipouya.com>
*/

@ExtendWith(MockitoExtension.class)
class DefaultSegmentIoAnalyticsTest {

    @Spy
    @InjectMocks
    private DefaultSegmentIoAnalytics segmentIoAnalytics;

    @Mock
    private PropertyUtils propertyUtils;

    @Test
    void testAfterPropertiesSet() {
        when(propertyUtils.getSegmentIoWriteApiKey()).thenReturn("SegmentIoAPIKey");
        segmentIoAnalytics.afterPropertiesSet();
        verify(propertyUtils, times(1)).getSegmentIoWriteApiKey();
    }

    @Test
    void testFireEvent() {
        segmentIoAnalytics.analytics = mock(Analytics.class);
        segmentIoAnalytics.fireEvent(SegmentIoAnalytics.EventType.CURRENT, "1", Map.of());
        verify(segmentIoAnalytics.analytics, timeout(1)).enqueue(isA(MessageBuilder.class));
    }
}
