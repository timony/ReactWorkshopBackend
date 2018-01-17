package com.tieto.mikactom.reactworkshop.backend.common.validation.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BindingResultErrorResponseMapper {

    @Autowired
    MessageSource messageSource;

    public ConstraintViolationResponse getResponse(HttpServletRequest request, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return ImmutableConstraintViolationResponse.builder()
                .message(messageSource.getMessage("error.validationError", null, request.getLocale()))
                .addAllFieldErrors(mapFieldErrors(request, fieldErrors))
                .url(request.getRequestURI())
                .build();
    }

    private List<FieldValidationError> mapFieldErrors(HttpServletRequest request, List<FieldError> source) {
        return source.stream().map(fieldError -> mapFieldError(request, fieldError))
                .collect(Collectors.toList());
    }

    private FieldValidationError mapFieldError(HttpServletRequest request, FieldError fieldError) {
        return ImmutableFieldValidationError.builder()
                .rejectedValue(Optional.ofNullable(fieldError.getRejectedValue()))
                .message(messageSource.getMessage(fieldError.getCode(), fieldError.getArguments(),
                        fieldError.getDefaultMessage(), request.getLocale()))
                .path(fieldError.getField())
                .build();
    }
}
