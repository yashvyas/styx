/*
  Copyright (C) 2013-2019 Expedia Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.hotels.styx.common.format;

import com.hotels.styx.api.HttpHeaders;
import com.hotels.styx.api.HttpMethod;
import com.hotels.styx.api.HttpRequest;
import com.hotels.styx.api.HttpResponse;
import com.hotels.styx.api.HttpResponseStatus;
import com.hotels.styx.api.HttpVersion;
import com.hotels.styx.api.LiveHttpRequest;
import com.hotels.styx.api.LiveHttpResponse;
import com.hotels.styx.api.Url;

import static java.util.Objects.requireNonNull;

/**
 * Formats requests and responses so that the headers are sanitised using the provided {@link SanitisedHttpHeaderFormatter}.
 */
public class SanitisedHttpMessageFormatter implements HttpMessageFormatter {

    private static final String NULL = "null";
    private final SanitisedHttpHeaderFormatter sanitisedHttpHeaderFormatter;

    public SanitisedHttpMessageFormatter(SanitisedHttpHeaderFormatter sanitisedHttpHeaderFormatter) {
        this.sanitisedHttpHeaderFormatter = requireNonNull(sanitisedHttpHeaderFormatter);
    }

    @Override
    public String formatRequest(HttpRequest request) {
        return request == null ? NULL
            : formatRequest(
                request.version(),
                request.method(),
                request.url(),
                request.id(),
                request.headers());
    }

    @Override
    public String formatRequest(LiveHttpRequest request) {
        return request == null ? NULL
            : formatRequest(
                request.version(),
                request.method(),
                request.url(),
                request.id(),
                request.headers());
    }

    @Override
    public String formatResponse(HttpResponse response) {
        return response == null ? NULL
            : formatResponse(
                response.version(),
                response.status(),
                response.headers());
    }

    @Override
    public String formatResponse(LiveHttpResponse response) {
        return response == null ? NULL
            : formatResponse(
                response.version(),
                response.status(),
                response.headers());
    }

    private String formatRequest(HttpVersion version, HttpMethod method, Url url, Object id, HttpHeaders headers) {
        return "{version=" + version
            + ", method=" + method
            + ", uri=" + url
            + ", headers=[" + sanitisedHttpHeaderFormatter.format(headers)
            + "], id=" + id + "}";
    }

    private String formatResponse(HttpVersion version, HttpResponseStatus status, HttpHeaders headers) {
        return "{version=" + version
            + ", status=" + status
            + ", headers=[" + sanitisedHttpHeaderFormatter.format(headers) + "]}";
    }

}
