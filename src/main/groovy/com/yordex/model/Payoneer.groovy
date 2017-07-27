package com.yordex.model

import com.fasterxml.jackson.annotation.JsonProperty
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
class Payoneer extends RestResource {

    String id

    String boardingStatus

    @JsonProperty("url")
    String redirectUrl

    @JsonProperty("expires")
    String expiryTime

    String orderId

    static boolean requestAccount(RequestOptions requestOptions) throws ApiException, ClientException {
        postRequest(Resources.PAYONEER_RESOURCE, null, Void.class, requestOptions)
        return true
    }

    static Payoneer instructions(String instruction, String orderId, RequestOptions requestOptions) throws ApiException, ClientException {
        def queryParams = ['action': instruction]
        def params = Params.builder().queryParams(queryParams).build()
        return postRequest(Resources.PAYONEER_INSTRUCTION_RESOURCE, builder().orderId(orderId).build(), Payoneer.class,
                           requestOptions, params)
    }
}
