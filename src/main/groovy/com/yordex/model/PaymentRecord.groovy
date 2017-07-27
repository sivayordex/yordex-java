package com.yordex.model

import com.yordex.error.ApiException
import com.yordex.error.ClientException
import com.yordex.http.Params
import com.yordex.http.RequestOptions
import com.yordex.http.Resources
import com.yordex.http.RestResource
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
@EqualsAndHashCode
final class PaymentRecord extends RestResource {

    Integer receivedCurrencyExponent

    Integer currencyExponent

    BigDecimal exchangeRate

    String orderId

    String eventId

    Integer amountInCents

    String currency

    String date

    String type

    String service

    Integer receivedAmountInCents

    String receivedCurrency

    String description

    String feePayer

    static Set<PaymentRecord> get(String orderId, RequestOptions requestOptions) throws ApiException, ClientException {
        def queryParams = ['orderId': orderId]
        def params = Params.builder().queryParams(queryParams).build()
        def paymentRecordWrapper = getRequest(Resources.PAYMENT_RECORD_RESOURCE, PaymentRecordWrapper.class, requestOptions, params)

        return paymentRecordWrapper.paymentRecords
    }

    @ToString
    @EqualsAndHashCode
    private static final class PaymentRecordWrapper {
        List<PaymentRecord> paymentRecords = new ArrayList<>()

        List<PaymentRecord> getPaymentRecords() {
            return paymentRecords
        }

        void setPaymentRecords(List<PaymentRecord> paymentRecords) {
            this.paymentRecords = paymentRecords
        }
    }
}
