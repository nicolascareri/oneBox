package com.example.onebox.utils;

import com.example.onebox.configuration.ContextWrapper;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtils {

    public static String getMessage(String key) {
        return ContextWrapper.getContext()
                .getMessage(key, null, Locale.ENGLISH);
    }

    public static String getMessage(String key, Locale lang) {
        return ContextWrapper.getContext().getMessage(key, null, lang);
    }

    public static String getMessage(String key, String... args) {
        return ContextWrapper.getContext().getMessage(key, args, Locale.ENGLISH);
    }

    public static String getMessage(String key, Locale lang, String... args) {
        return ContextWrapper.getContext().getMessage(key, args, lang);
    }

}
