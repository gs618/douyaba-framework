package com.github.douyaba.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author s.c.gao
 */
public class Constants {

    private Constants() {
    }

    public static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .create();
}
