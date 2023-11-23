package com.github.nicolasholanda.newspaper.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class WhatsappService {

    @Value("${twilio.auth.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;

    @Value("${twilio.default-number}")
    private String DEFAULT_SENDER_NUMBER;

    public void sendTextMessageToPhoneNumber(String content, String phoneNumber) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
            formatNumberForWhatsapp(phoneNumber),
            formatNumberForWhatsapp(DEFAULT_SENDER_NUMBER),
            content
        ).create();
    }

    private PhoneNumber formatNumberForWhatsapp(String phoneNumber) {
        return new PhoneNumber(format("whatsapp:%s", phoneNumber));
    }
}
