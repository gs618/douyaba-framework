package com.github.douyaba.exception.api.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Constant {

    static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .create();

}
