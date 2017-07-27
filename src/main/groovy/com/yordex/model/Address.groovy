package com.yordex.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
@EqualsAndHashCode
final class Address {

    String address1

    String address2

    String address3

    String postalCode

    String city

    String state

    String countryCode
}
