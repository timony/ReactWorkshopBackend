package com.tieto.mikactom.reactworkshop.backend.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = ImmutableCustomerResponse.class, builder = ImmutableCustomerResponse.Builder.class)
public interface CustomerResponse {

    Long getId();

    String getFirstname();

    String getLastname();

    String getEmail();

    boolean isEnabled();

    Optional<LocalDateTime> getRegistrationDate();
}
