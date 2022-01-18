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
