package com.fibanez.spring4basic.utils;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;

public final class TestUtil {

    private TestUtil() {}

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

}
