package com.tieto.mikactom.reactworkshop.backend.common.validation.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonDeserialize(as = ImmutableConstraintViolationResponse.class)
public interface ConstraintViolationResponse extends ErrorResponseBase {

    List<FieldValidationError> getFieldErrors();
}
