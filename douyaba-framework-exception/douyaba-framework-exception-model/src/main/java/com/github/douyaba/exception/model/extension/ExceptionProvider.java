package com.github.douyaba.exception.model.extension;

import com.github.douyaba.exception.model.bo.ExceptionBO;

/**
 * @author s.c.gao
 */
public interface ExceptionProvider {

    /**
     * code 查找异常信息
     *
     * @param code
     * @return
     */
    ExceptionBO findByCode(int code);

}
