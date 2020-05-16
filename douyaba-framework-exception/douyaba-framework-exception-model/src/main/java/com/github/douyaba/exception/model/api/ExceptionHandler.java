package com.github.douyaba.exception.model.api;


import com.github.douyaba.exception.model.bo.ApplicationException;
import com.github.douyaba.exception.model.bo.I18nMessage;
import com.github.douyaba.web.protocol.model.bo.Payload;

/**
 * @author s.c.gao
 */
public interface ExceptionHandler {

    /**
     * 异常转WebProtocol
     *
     * @param exception ex
     * @return payload
     */
    Payload<I18nMessage> toWebProtocol(ApplicationException exception);

}
