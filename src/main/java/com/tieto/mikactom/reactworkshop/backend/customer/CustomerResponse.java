package com.tieto.mikactom.reactworkshop.backend.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.immutables.value.Value;

import java.util.Date;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
public interface CustomerResponse {

    Long getId();

    String getFirstname();

    String getLastname();

    String getEmail();

    boolean isEnabled();

    Date getRegistrationDate();
}
