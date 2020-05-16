package com.github.douyaba.exception.model;

import com.github.douyaba.exception.model.api.ExceptionHandler;
import com.github.douyaba.exception.model.bo.ApplicationException;
import com.github.douyaba.exception.model.bo.ExceptionBO;
import com.github.douyaba.exception.model.bo.I18nMessage;
import com.github.douyaba.exception.model.extension.ExceptionProvider;
import com.github.douyaba.web.protocol.model.bo.Payload;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author s.c.gao
 */
@Slf4j
public class DefaultExceptionHandlerImpl implements ExceptionHandler {

    @Resource
    private ExceptionProvider exceptionProvider;

    @Override
    public Payload<I18nMessage> toWebProtocol(ApplicationException exception) {
        log.error(exception.getErrorMessage().getPayload(), exception.getErrorMessage().getParameters());
        ExceptionBO exceptionBO = exceptionProvider.findByCode(exception.getCode());
        return Payload.<I18nMessage>newInstance()
                .setCode(exception.getCode())
                .setData(I18nMessage.newInstance().setPayload(exceptionBO.getI18nMessage()))
                .setSuccess(false);
    }

}
