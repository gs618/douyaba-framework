package com.github.douyaba.exception.model.bo;

import com.github.douyaba.common.util.Constants;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author s.c.gao
 */
@Data
@Accessors(chain = true)
public class ApplicationException extends RuntimeException {

    protected Integer code;
    protected Message errorMessage;

    private ApplicationException() {
        super();
    }

    public static ApplicationException newInstance(int code) {
        return new ApplicationException().setCode(code);
    }

    @Override
    public String toString() {
        return Constants.GSON.toJson(this);
    }

}
