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
package org.sourcelab.activecampaign;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcelab.activecampaign.event.Event;
import org.sourcelab.activecampaign.event.EventApiConfig;
import org.sourcelab.activecampaign.event.EventClient;
import org.sourcelab.activecampaign.event.SubmitEventResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration test of client API.
 */
@Tag("IntegrationTest")
class EventClientTest {
    private static final Logger logger = LoggerFactory.getLogger(EventClientTest.class);

    private static EventClient eventClient = null;

    @BeforeAll
    static void setup() throws IOException {
        final InputStream inputStream = EventClientTest.class.getClassLoader().getResourceAsStream("test_credentials.properties");


        // Load properties
        final Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();

        final String activeCampaignAccountId = properties.getProperty("account_id");
        final String activeCampaignEventKey = properties.getProperty("event_key");

        if ((activeCampaignAccountId == null || activeCampaignEventKey == null)) {
            throw new RuntimeException(
                    "To run the integration tests you must create a resource file named test_credentials.properties which contains the properties:\n"
                            + " account_name \n"
                            + " api_token\n\n"
            );
        }

        /**
         * Set during class bootstrap.
         */
        final EventApiConfig apiConfig = new EventApiConfig();
        eventClient = new EventClient(apiConfig, activeCampaignAccountId, activeCampaignEventKey);
    }

    /**
     * Cleanup.
     */
    @AfterAll
    static void tearDown() {
        if (eventClient != null) {
            eventClient.close();
            eventClient = null;
        }
    }

    /**
     * Smoke test about me end point.
     */
    @Test
    void testPostEvent() {
        Event event = new Event("user_created", "", "example@example.com");
        SubmitEventResponse submitEventResponse = eventClient.submitEvent(event);
        logger.info("V1: {}", submitEventResponse);

        assertEquals(submitEventResponse.getResultCode(), 1);
        assertEquals(submitEventResponse.getResultMessage().toLowerCase(), "event spawned");
    }
}