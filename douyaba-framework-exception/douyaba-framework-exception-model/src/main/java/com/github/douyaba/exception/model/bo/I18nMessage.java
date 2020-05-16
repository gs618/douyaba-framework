package com.github.douyaba.exception.model.bo;

import com.github.douyaba.common.util.Constants;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * @author s.c.gao
 */
@Data
@Accessors(chain = true)
public class I18nMessage implements Serializable {

    /**
     * Map<Lang, message>, for example
     * payload.put("CN", "你好，中国");
     * payload.put("EN", "Hello world!");
     *
     */
    private Map<String, String> payload;

    private String[] parameters;

    private I18nMessage() {
    }

    public static I18nMessage newInstance() {
        return new I18nMessage();
    }

    @Override
    public String toString() {
        return Constants.GSON.toJson(this);
    }
}
