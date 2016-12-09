package com.drivertracker.messages;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageResources {
    private MessageSource messageSource;
    private static final Locale defaultLocale = new Locale("US");
    private final Locale locale;

    @Autowired
    public MessageResources(final MessageSource messageSource) {
        this(messageSource, defaultLocale);
    }

    public MessageResources(final MessageSource messageSource, final Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public String getMsg(String key) {
        return messageSource.getMessage(String.valueOf(key), null, locale);
    }
}
