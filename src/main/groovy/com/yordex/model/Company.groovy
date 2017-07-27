package com.yordex.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
@EqualsAndHashCode
final class Company {

    String companyTradingName

    Address companyTradingAddress

    Address companyRegisteredAddress

    String companyRegisteredName

    String companyType

    String registrationNumber

    String website

    BankAccount bankAccount

    Set<RelevantIndividual> relevantIndividuals = new HashSet<>()
}
