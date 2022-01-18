/**
 * Copyright 2019, 2020, 2021 SourceLab.org https://github.com/SourceLabOrg/activecampaign-java-client
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.sourcelab.activecampaign.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.sourcelab.activecampaign.JacksonFactory;
import org.sourcelab.http.rest.request.PostRequest;
import org.sourcelab.http.rest.request.RequestMethod;
import org.sourcelab.http.rest.request.body.RequestBodyContent;
import org.sourcelab.http.rest.request.body.UrlEncodedFormBodyContent;

import java.io.IOException;

/**
 * Represents an account list request.
 */
public class SubmitEventRequest implements PostRequest<SubmitEventResponse> {

    private final Event event;
    private final String accountId;
    private final String eventKey;

    public SubmitEventRequest(Event event, String accountId, String eventKey) {
        this.event = event;
        this.accountId = accountId;
        this.eventKey = eventKey;
    }

    @Override
    public String getApiEndpoint() {
        return "";
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.POST;
    }

    @Override
    public RequestBodyContent getRequestBody() {

        UrlEncodedFormBodyContent urlEncodedFormBodyContent = new UrlEncodedFormBodyContent();
        urlEncodedFormBodyContent.addParameter("actid", this.accountId);
        urlEncodedFormBodyContent.addParameter("key", this.eventKey);
        urlEncodedFormBodyContent.addParameter("event", event.getEvent());
        urlEncodedFormBodyContent.addParameter("eventdata", event.getEventData());
        String targetEmail = event.getTargetEmail();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("email", targetEmail);

        urlEncodedFormBodyContent.addParameter("visit", node.toString());
        return urlEncodedFormBodyContent;
    }

    @Override
    public SubmitEventResponse parseResponse(final String response) throws IOException {
        return JacksonFactory.newInstance().readValue(response, SubmitEventResponse.class);
    }
}
