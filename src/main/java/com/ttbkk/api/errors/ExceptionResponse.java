package com.ttbkk.api.errors;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ExceptionResponse {
    String description;
    String code;
    String stacktrace;

    public ExceptionResponse(BaseException e) {
        this.code = e.getStatus().name();
        this.description = e.getMessage();
        this.stacktrace = String.join(
            System.lineSeparator(),
            Arrays
                .stream(e.getStackTrace())
                .map(
                    trace -> String.format(
                        "%s, %s, %s",
                        trace.getFileName(),
                        trace.getMethodName(),
                        trace.getLineNumber()
                        )
                ).collect(Collectors.joining())
        );
    }
}
