package org.sourcelab.activecampaign.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmitEventResponse extends AbstractResponse {

    @JsonCreator
    public SubmitEventResponse(
            @JsonProperty("success") final int success,
            @JsonProperty("message") final String message
    ) {
        super(success, message);
    }

    @Override
    public String toString() {
        return "SubmitEventResponse{" +
                "resultCode=" + resultCode +
                ", resultMessage='" + resultMessage + '\'' +
                '}';
    }
}
