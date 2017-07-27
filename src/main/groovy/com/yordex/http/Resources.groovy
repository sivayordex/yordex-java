package com.yordex.http

final class Resources {

    static final TRADER_RESOURCE = "/traders"

    static final ORDER_RESOURCE = "/orders"

    static final ORDER_APPROVAL_RESOURCE = ORDER_RESOURCE + "/{orderId}/approvals"

    static final PAYMENT_RECORD_RESOURCE = "/paymentrecords"

    static final PAYONEER_RESOURCE = "/payoneer"

    static final PAYONEER_INSTRUCTION_RESOURCE = PAYONEER_RESOURCE + "/instructions"
}
