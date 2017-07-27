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
final class Order extends RestResource {

    String id

    String buyerId

    String sellerId

    String description

    Integer orderAmountInCents

    String orderCurrency

    String sellerOrderReference

    String buyerOrderReference

    String sellerCurrency

    String buyerCurrency

    String terms

    String status

    String creationDate

    String modificationDate

    Integer orderCurrencyExponent

    String nextApprover

    String sellerCompanyTradingName

    String buyerCompanyTradingName

    Set<Event> events = new HashSet<>()

    Set<Approval> approvals = new HashSet<>()

    Set<OrderService> services = new HashSet<>()

    static Order create(Order order, RequestOptions requestOptions) throws ApiException, ClientException {
        return postRequest(Resources.ORDER_RESOURCE, order, Order.class, requestOptions)
    }

    static Set<Approval> approveOrder(String orderId, String sellerApiKey, String buyerApiKey) throws ApiException, ClientException {
        def requestOptions = RequestOptions.defaults()
        def approval = Approval.builder().approved(true).build()

        def paramsMap = ['orderId': orderId]

        def params = Params.builder().pathParams(paramsMap).build()

        Set<Approval> approvals = new HashSet<>()

        //Seller Approve
        requestOptions.apiKey = sellerApiKey
        def sellerApproval = postRequest(Resources.ORDER_APPROVAL_RESOURCE, approval, Approval.class, requestOptions, params)
        approvals.add(sellerApproval)

        //Buyer Approve
        requestOptions.apiKey = buyerApiKey
        def buyerApproval = postRequest(Resources.ORDER_APPROVAL_RESOURCE, approval, Approval.class, requestOptions, params)
        approvals.add(buyerApproval)

        return approvals
    }
}
