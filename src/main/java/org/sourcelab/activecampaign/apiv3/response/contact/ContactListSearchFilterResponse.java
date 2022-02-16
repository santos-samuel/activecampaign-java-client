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
package org.sourcelab.activecampaign.apiv3.response.contact;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.sourcelab.activecampaign.apiv3.request.contact.Contact;
import org.sourcelab.activecampaign.apiv3.request.contact.ContactBuilder;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents the API response from creating a Contact.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactListSearchFilterResponse {

    private final List<Object> scoreValues;
    private final List<Contact> contacts;
    private final Map<String, Object> metaValues;

    /**
     * Constructor.
     */
    @JsonCreator
    public ContactListSearchFilterResponse(
            @JsonProperty("scoreValues") final List<Object> scoreValues,
            @JsonProperty("contacts") final List<Map<String, Object>> contacts,
            @JsonProperty("meta") final Map<String, Object> metaValues
    ) {
        this.scoreValues = scoreValues;
        this.contacts = contacts.stream().map(contact -> Utils.buildContactObject(contact, null)).collect(Collectors.toList());
        this.metaValues = metaValues;
    }

    public List<Object> getScoreValues() {
        return scoreValues;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Map<String, Object> getMetaValues() {
        return metaValues;
    }
}
