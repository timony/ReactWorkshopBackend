package com.tieto.mikactom.reactworkshop.backend.common.validation.response;

public interface ObjectValidationError {

    String getPath();

    String getMessage();
}
