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

import org.sourcelab.activecampaign.AbstractClient;
import org.sourcelab.activecampaign.exception.InvalidCredentialsException;
import org.sourcelab.http.rest.RestResponse;

public class EventClient extends AbstractClient {

    private final String activeCampaignAccountId;
    private final String activeCampaignEventKey;

    public EventClient(final EventApiConfig apiConfig, String activeCampaignAccountId, String activeCampaignEventKey) {
        super(apiConfig);
        this.activeCampaignAccountId = activeCampaignAccountId;
        this.activeCampaignEventKey = activeCampaignEventKey;
    }

    public SubmitEventResponse submitEvent(final Event event) {
        return submitRequest(new SubmitEventRequest(event, this.activeCampaignAccountId, this.activeCampaignEventKey));
    }

    @Override
    protected void validateResponseForInvalidCredentials(RestResponse restResponse) throws InvalidCredentialsException {
        // not needed
    }
}
