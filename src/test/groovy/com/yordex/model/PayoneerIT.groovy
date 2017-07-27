package com.yordex.model

import com.yordex.http.RequestOptions

class PayoneerIT extends BaseIT {

    private static final String PARTNER_KEY_FOR_SELLER = "3c5f54b1-96a2-459d-a54c-7c39fdafd307"

    def "can create payoneer account"() {
        when:
            def created = Payoneer.requestAccount(RequestOptions.builder().apiKey(PARTNER_KEY_FOR_SELLER).build())
        then:
            created
    }

    def "can get bank instructions"() {
        when:
            def instructions = Payoneer.instructions("bank_details", null, RequestOptions.builder().apiKey(PARTNER_KEY_FOR_SELLER).build())
        then:
            instructions != null
            instructions.expiryTime != null
            instructions.redirectUrl != null
    }
}
