package com.yordex.model

import com.yordex.http.RequestOptions
import spock.lang.Unroll

class OrderIT extends BaseIT {

    //Buyer Id, for partner partner1
    private static final String BUYER_ID = "bfec04df-b762-4394-bda0-50be11bb42f7"

    //Seller Id, for partner partner1
    private static final String SELLER_ID = "c2d92659-2540-4f9e-81c5-440d472cdf97"

    private static final String PARTNER_KEY_FOR_SELLER = "a8835643-e1a9-4943-b91a-de9e59219dac"

    private static final String PARTNER_KEY_FOR_BUYER = "a0ca9751-c196-40c4-8fcc-1e38b145c9a2"

    def "can seller create order"() {
        when:
            def order = createOrder()
        then:
            order != null
            order.id != null
            order.buyerId == BUYER_ID
            order.sellerId == SELLER_ID
    }

    def "can approve order"() {
        given:
            def order = createOrder()
        when:
            def approvals = Order.approveOrder(order.id, PARTNER_KEY_FOR_SELLER, PARTNER_KEY_FOR_BUYER)
        then:
            approvals != null
            approvals.size() == 2 as Integer
    }

    def "can add service to order"() {
        given:
            def order = createOrder()
        when:
            def added = Order.addService(order.id, "PAYONEER", [type: "GOODS"],
                                         RequestOptions.builder().apiKey(PARTNER_KEY_FOR_SELLER).build())
        then:
            added
    }

    def "can get order status"() {
        given:
            def order = createOrder()
            def added = Order.addService(order.id, "PAYONEER", [type: "GOODS"],
                                         RequestOptions.builder().apiKey(PARTNER_KEY_FOR_SELLER).build())
            RequestOptions requestOptions = RequestOptions.builder()
                    .apiKey(PARTNER_KEY_FOR_SELLER).build()

        when:
            def orderDetails = Order.get(order.id,requestOptions)
            System.out.println(orderDetails)
        then:
            orderDetails != null
            orderDetails.id != null
            orderDetails.buyerId == BUYER_ID
            orderDetails.sellerId == SELLER_ID
            orderDetails.events.size()==3
    }

    @Unroll
    def "can NOT add service to order"() {
        given:
            def order = createOrder()
        when:
            Order.addService(order.id, serviceName, null, RequestOptions.builder().apiKey(PARTNER_KEY_FOR_SELLER).build())
        then:
            def illegalArgumentException = thrown(IllegalArgumentException)
            illegalArgumentException.message == "Service name can't be blank"
        where:
            serviceName << ["", null, "  "]
    }

    private Order createOrder() {
        Order newOrder = Order.builder()
                .buyerId(BUYER_ID)
                .sellerId(SELLER_ID)
                .orderCurrency("GBP")
                .orderAmountInCents(12222)
                .terms("Full payment upfront") // So that we can approve order, without creating events in test
                .build()
        RequestOptions requestOptions = RequestOptions.builder()
                .apiKey(PARTNER_KEY_FOR_SELLER).build()
        return Order.create(newOrder, requestOptions)
    }
}
