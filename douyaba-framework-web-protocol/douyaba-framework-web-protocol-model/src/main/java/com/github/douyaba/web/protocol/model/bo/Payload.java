package com.github.douyaba.web.protocol.model.bo;

import com.github.douyaba.common.util.Constants;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author s.c.gao
 */
@Data
@Accessors(chain = true)
public class Payload<T> implements Serializable {

    protected Integer code;
    protected T data;
    protected Boolean success;

    private Payload() {
    }

    public static <T> Payload<T> newInstance() {
        return new Payload<>();
    }

    @Override
    public String toString() {
        return Constants.GSON.toJson(this);
    }
}
