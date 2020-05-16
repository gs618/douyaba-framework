package com.github.douyaba.exception.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author sgao
 */
@Data
public class HelloDTO {

    @JsonProperty("Name")
    private String Name;

    @Override
    public String toString() {
        return Constant.GSON.toJson(this);
    }
}
