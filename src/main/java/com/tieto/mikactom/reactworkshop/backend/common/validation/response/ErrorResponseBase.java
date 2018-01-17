package com.tieto.mikactom.reactworkshop.backend.common.validation.response;

import java.util.Optional;

public interface ErrorResponseBase {

    String getMessage();

    Optional<String> getUrl();
}
