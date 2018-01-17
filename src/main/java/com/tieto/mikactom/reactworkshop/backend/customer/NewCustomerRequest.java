package com.tieto.mikactom.reactworkshop.backend.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.valuehandling.UnwrapValidatedValue;
import org.immutables.value.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@Value.Immutable
@JsonDeserialize(as = ImmutableNewCustomerRequest.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface NewCustomerRequest {

    @NotNull
    @Size(min = 1, max = 255)
    @UnwrapValidatedValue
    Optional<String> getFirstname();

    @NotNull
    @Size(min = 1, max = 255)
    @UnwrapValidatedValue
    Optional<String> getLastname();

    @NotNull
    @Size(min = 1, max = 255)
    @UnwrapValidatedValue
    Optional<String> getEmail();

}
