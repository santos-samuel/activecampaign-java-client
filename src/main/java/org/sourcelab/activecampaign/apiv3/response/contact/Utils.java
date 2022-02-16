package org.sourcelab.activecampaign.apiv3.response.contact;

import org.sourcelab.activecampaign.apiv3.request.contact.Contact;
import org.sourcelab.activecampaign.apiv3.request.contact.ContactBuilder;

import java.util.List;
import java.util.Map;

public class Utils {
    public static Contact buildContactObject(Map<String, Object> contactValues, List<Contact.FieldValue> fieldValues) {
        // Build contact
        final ContactBuilder builder = Contact.newBuilder()
                .withPhone((String) contactValues.get("phone"))
                .withFirstName((String) contactValues.get("firstName"))
                .withLastName((String) contactValues.get("lastName"))
                .withEmail((String) contactValues.get("email"))
                .withId((String) contactValues.get("id"));

        if (fieldValues != null) {
            fieldValues.forEach((fieldValue) -> builder.withField(fieldValue.getField(), fieldValue.getValue()));
        }

        if (contactValues.containsKey("links")) {
            builder.withLinks((Map<String, String>) contactValues.get("links"));
        }

        return builder.build();
    }
}
