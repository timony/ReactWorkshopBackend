package com.tieto.mikactom.reactworkshop.backend.common.validation.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@JsonDeserialize(as = ImmutableFieldValidationError.class)
public interface FieldValidationError extends ObjectValidationError {

    Optional<Object> getRejectedValue();
}
