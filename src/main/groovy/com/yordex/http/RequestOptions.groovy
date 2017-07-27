package com.yordex.http

import com.yordex.Yordex
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Canonical
@Builder(excludes = "secured")
@ToString
@EqualsAndHashCode
final class RequestOptions {

    static RequestOptions defaults() {
        return new RequestOptions(apiKey: Yordex.apiKey, secured: true)
    }

    String apiKey

    boolean secured = true
}
