package com.yordex.model

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY

@ToString
@EqualsAndHashCode
@Builder
@Canonical
final class ApiError {

    String title

    Integer status

    String detail

    String traceId

    List<Error> errors = new ArrayList<>(6)

    @JsonInclude(NON_EMPTY)
    @ToString
    @EqualsAndHashCode
    @Builder
    @Canonical
    static class Error {
        String field

        Object rejected

        String message
    }
}
