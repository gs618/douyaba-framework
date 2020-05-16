package com.github.douyaba.starter.web.protocol;

import com.github.douyaba.common.util.Constants;
import com.github.douyaba.web.protocol.model.bo.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author s.c.gao
 */
@Slf4j
public class AutoResponseWrapper implements HandlerMethodReturnValueHandler {

    private List<Method> apiMethods;

    public AutoResponseWrapper() {
        apiMethods = new LinkedList<>();
    }

    public AutoResponseWrapper(List<Method> apiMethods) {
        this.apiMethods = apiMethods;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return apiMethods.contains(methodParameter.getMethod());
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        modelAndViewContainer.setRequestHandled(true);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        try (PrintWriter writer = response.getWriter()) {
            writer.print(Constants.GSON.toJson(
                    Payload.newInstance().setCode(200).setData(o).setSuccess(true)));
            writer.flush();
        } catch (IOException e) {
            log.error("Result wrapper in error");
            throw e;
        }
    }
}
