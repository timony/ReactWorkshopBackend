package com.tieto.mikactom.reactworkshop.backend.common.validation;

import com.tieto.mikactom.reactworkshop.backend.common.validation.response.BindingResultErrorResponseMapper;
import com.tieto.mikactom.reactworkshop.backend.common.validation.response.ConstraintViolationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerErrorAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerErrorAdvice.class);

    @Autowired
    BindingResultErrorResponseMapper methodArgumentNotValidExceptionMapper;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ConstraintViolationResponse handleMethodArgumentNotValidException(
            HttpServletRequest request,
            MethodArgumentNotValidException ex
    ) {
        LOG.debug("Validation error(s) occurred", ex);
        return methodArgumentNotValidExceptionMapper.getResponse(request, ex.getBindingResult());
    }

}
