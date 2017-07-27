package com.yordex.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
@EqualsAndHashCode
final class BankAccount {

    String bankName

    String accountHolderName

    String accountNumber

    String sortCode

    String iban

    String routingNumber

    String accountType

    String swiftCode

    String bic

    String bankCode

    String branchCode

    String ifscCode

    String cardNumber

    String region

    String bsbCode

    String institutionNumber

    String transitNumber

    String taxRegistrationNumber

    Address address
}
