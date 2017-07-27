package com.yordex.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@EqualsAndHashCode
@ToString(excludes = "password")
final class User {

    String email

    String password
}
