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

}
