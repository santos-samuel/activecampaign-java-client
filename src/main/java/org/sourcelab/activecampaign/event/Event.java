package org.sourcelab.activecampaign.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    @JsonProperty("event")
    private final String event;

    @JsonProperty("eventdata")
    private final String eventData;

    @JsonProperty("visit")
    private final String targetEmail;

    @JsonCreator
    public Event(
            final String event,
            final String eventData,
            String targetEmail) {
        this.event = event;
        this.eventData = eventData;
        this.targetEmail = targetEmail;
    }

    public String getEvent() {
        return event;
    }

    public String getEventData() {
        return eventData;
    }

    public String getTargetEmail() {
        return targetEmail;
    }

    @Override
    public String toString() {
        return "Event{" +
                "event='" + event + '\'' +
                ", eventData='" + eventData + '\'' +
                ", targetEmail='" + targetEmail + '\'' +
                '}';
    }
}
