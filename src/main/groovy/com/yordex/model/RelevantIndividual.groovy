package com.yordex.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
@EqualsAndHashCode
final class RelevantIndividual {

    Integer pctOwnership;

    String role;

    String title;

    String firstName;

    String lastName;

    String middleNames;

    String dateOfBirth;

    Address homeAddress;

    String email

    String phone
}
