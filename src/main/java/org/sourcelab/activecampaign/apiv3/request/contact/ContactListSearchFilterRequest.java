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
package org.sourcelab.activecampaign.apiv3.request.contact;

import org.sourcelab.activecampaign.JacksonFactory;
import org.sourcelab.activecampaign.apiv3.response.contact.ContactListSearchFilterResponse;
import org.sourcelab.http.rest.request.Request;
import org.sourcelab.http.rest.request.RequestMethod;
import org.sourcelab.http.rest.request.body.NoBodyContent;
import org.sourcelab.http.rest.request.body.RequestBodyContent;

import java.io.IOException;
import java.util.Map;

/**
 * Represents an account create request.
 */
public class ContactListSearchFilterRequest implements Request<ContactListSearchFilterResponse> {

    private final Map<String, String> queryParams;

    public ContactListSearchFilterRequest(final Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    @Override
    public String getApiEndpoint() {
        String base = "api/3/contacts";
        String queryParamsString = "";
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (queryParamsString.length() == 0) {
                queryParamsString += "?";
            }
            queryParamsString += entry.getKey() + "=" + entry.getValue() + "&";
        }

        if (queryParamsString.length() > 0) {
            queryParamsString = queryParamsString.substring(0, queryParamsString.length() - 1);
        }
        return base + queryParamsString;
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.GET;
    }

    @Override
    public RequestBodyContent getRequestBody() {
        return new NoBodyContent();
    }

    @Override
    public ContactListSearchFilterResponse parseResponse(final String response) throws IOException {
        return JacksonFactory.newInstance().readValue(response, ContactListSearchFilterResponse.class);
    }
}
