package com.tieto.mikactom.reactworkshop.backend.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.immutables.value.Value;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
public interface NewCustomerRequest {

    String getFirstname();
    String getLastname();
    String getEmail();

}
