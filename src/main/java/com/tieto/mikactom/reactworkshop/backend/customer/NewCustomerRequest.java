package com.tieto.mikactom.reactworkshop.backend.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.NotEmpty;
import org.immutables.value.Value;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = ImmutableNewCustomerRequest.class)
public interface NewCustomerRequest {

    @NotEmpty
    String getFirstname();

    @NotEmpty
    String getLastname();

    @NotEmpty
    String getEmail();

}
