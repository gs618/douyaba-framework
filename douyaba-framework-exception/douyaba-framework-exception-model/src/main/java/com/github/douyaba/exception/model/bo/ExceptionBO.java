package com.github.douyaba.exception.model.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author s.c.gao
 */
@Data
@Accessors(chain = true)
public class ExceptionBO {

    private Integer code;

    private Map<String, String> i18nMessage;
}
