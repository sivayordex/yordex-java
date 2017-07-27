package com.yordex.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
@EqualsAndHashCode
final class OrderService {

    String serviceName

    Map<String, String> tags = new HashMap<>()
}
