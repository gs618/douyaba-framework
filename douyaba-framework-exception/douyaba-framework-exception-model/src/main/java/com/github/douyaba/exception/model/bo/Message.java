package com.github.douyaba.exception.model.bo;

import com.github.douyaba.common.util.Constants;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author s.c.gao
 */
@Data
@Accessors(chain = true)
public class Message implements Serializable {

    private String payload;

    private String[] parameters;

    private Message() {
    }

    public static Message newInstance() {
        return new Message();
    }

    @Override
    public String toString() {
        return Constants.GSON.toJson(this);
    }
}
