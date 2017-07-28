package com.yordex.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
@EqualsAndHashCode
final class Event {

    String id

    String paymentDueDate

    Integer pctToBePaid

    Integer creditTerms

    Set<String> documentsRequired = new HashSet<>()

    String eventName

    String paymentName

    Set<String> confirmers = new HashSet<>()

    Integer eventNumber

    String creditTermsFrom

    Integer amountToBePaidInCents

    Integer receivedAmountInCents

    String status

    boolean confirmed

    String invoiceDate

    String invoiceReference

    Set<EventConfirmation> confirmations = new HashSet<>()

    String paymentType
}
