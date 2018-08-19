package com.madadipouya.eris.integration.groupkt.remote.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupktCountryNameResponse {

    public GroupktCountryNameResponse() {
        restResponse = new RestResponse();
    }

    @JsonProperty("RestResponse")
    private RestResponse restResponse;

    public RestResponse getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }

    public static class RestResponse {

        public RestResponse() {
            messages = List.of();
            result = new Result();
        }

        @JsonProperty("messages")
        private List<String> messages;

        @JsonProperty("result")
        private Result result;

        public List<String> getMessages() {
            return messages;
        }

        public void setMessages(List<String> messages) {
            this.messages = messages;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }
    }

    public static class Result {

        public Result(String name, String alpha2Code, String alpha3Code) {
            this.name = name;
            this.alpha2Code = alpha2Code;
            this.alpha3Code = alpha3Code;
        }

        public Result() {
            this("", "", "");
        }

        @JsonProperty("name")
        private String name;

        @JsonProperty("alpha2_code")
        private String alpha2Code;

        @JsonProperty("alpha3_code")
        private String alpha3Code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlpha2Code() {
            return alpha2Code;
        }

        public void setAlpha2Code(String alpha2Code) {
            this.alpha2Code = alpha2Code;
        }

        public String getAlpha3Code() {
            return alpha3Code;
        }

        public void setAlpha3Code(String alpha3Code) {
            this.alpha3Code = alpha3Code;
        }
    }
}
