package com.yordex.http

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
@EqualsAndHashCode
class Params {

    Map<String, String> pathParams

    Map<String, Object> queryParams
}
