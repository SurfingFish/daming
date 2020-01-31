package com.thebund1st.daming.adapter.jackson.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

import static com.thebund1st.daming.application.domain.SmsVerificationCode.smsVerificationCodeOf;

public class SmsVerificationCodeJsonDeserializer extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return smsVerificationCodeOf(p.getValueAsString());
    }
}
