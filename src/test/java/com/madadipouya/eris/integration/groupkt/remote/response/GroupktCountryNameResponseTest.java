package com.madadipouya.eris.integration.groupkt.remote.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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

class GroupktCountryNameResponseTest {

    @Test
    void testGroupktCountryNameResponse() {
        GroupktCountryNameResponse groupktResponse = new GroupktCountryNameResponse();
        GroupktCountryNameResponse.RestResponse restResponse = new GroupktCountryNameResponse.RestResponse();
        GroupktCountryNameResponse.Result result = new GroupktCountryNameResponse.Result();
        result.setAlpha2Code("CA");
        result.setAlpha3Code("CAN");
        result.setName("Canada");
        restResponse.setMessages(List.of("Message1", "Message2"));
        restResponse.setResult(result);
        groupktResponse.setRestResponse(restResponse);
        assertNotNull(groupktResponse.getRestResponse());
        assertNotNull(groupktResponse.getRestResponse().getMessages());
        assertEquals(2, groupktResponse.getRestResponse().getMessages().size());
        assertEquals("Message1", groupktResponse.getRestResponse().getMessages().get(0));
        assertEquals("Message2", groupktResponse.getRestResponse().getMessages().get(1));
        assertNotNull(groupktResponse.getRestResponse().getResult());
        assertEquals("CA", groupktResponse.getRestResponse().getResult().getAlpha2Code());
        assertEquals("CAN", groupktResponse.getRestResponse().getResult().getAlpha3Code());
        assertEquals("Canada", groupktResponse.getRestResponse().getResult().getName());
    }
}
