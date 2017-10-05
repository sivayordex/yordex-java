package com.yordex.model

import com.fasterxml.jackson.annotation.JsonUnwrapped
import com.yordex.error.ApiException
import com.yordex.error.ClientException
import com.yordex.http.RequestOptions
import com.yordex.http.Resources
import com.yordex.http.RestResource
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
@EqualsAndHashCode
final class Trader extends RestResource {

    String id

    String partnerId

    String traderSlug

    Company company

    User user

    String traderStatus

    String connectionApikey

    @JsonUnwrapped
    Company getCompany() {
        return company
    }

    String email

    @JsonUnwrapped
    void setCompany(Company company) {
        this.company = company
    }

    static Trader create(Trader trader) throws ApiException, ClientException {
        RequestOptions requestOptions = RequestOptions.defaults()
        requestOptions.secured = false
        return postRequest(Resources.TRADER_RESOURCE, trader, Trader.class, requestOptions)
    }
}

