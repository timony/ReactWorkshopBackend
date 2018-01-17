package com.tieto.mikactom.reactworkshop.backend.common.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

/**
 * Represents a rest response for generic use.
 * Will typically get extended, but can also stand for itself if no more specific info is needed.
 * <br>
 * Will come in handy especially when you need to return specific data, but you also must support error responses.
 */
@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
public interface RestResponse {

    String getId();

    String getMessage();

    Optional<Integer> getVersion();

    Optional<List<String>> getErrors();

}
