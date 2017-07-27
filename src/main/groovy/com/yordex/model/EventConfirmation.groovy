package com.yordex.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
@EqualsAndHashCode
final class EventConfirmation {

    String date;

    String confirmerTraderId;
}
