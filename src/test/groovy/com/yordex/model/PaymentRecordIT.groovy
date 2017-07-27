package com.yordex.model

import com.yordex.http.RequestOptions

class PaymentRecordIT extends BaseIT {

    //Seller Id, for partner partner1
    private static final String SELLER_ID = "538967e6-875a-4674-a72b-9e9c87e52bbe"

    private static final String PARTNER_KEY_FOR_SELLER = "3c5f54b1-96a2-459d-a54c-7c39fdafd307"

    def "can get Payment records for order Id"() {
        given:
            def requestOptions = RequestOptions.builder().apiKey(PARTNER_KEY_FOR_SELLER).build()
        when:
            def paymentRecords = PaymentRecord.get("5054044f-2c39-4fb6-adae-3aee9721279a", requestOptions)
        then:
            paymentRecords != null
    }
}
