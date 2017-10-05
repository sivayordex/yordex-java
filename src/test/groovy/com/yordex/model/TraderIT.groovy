package com.yordex.model

import com.yordex.error.ApiException

class TraderIT extends BaseIT {

    def "can NOT create trader"() {
        given:
            def trader = Trader.builder().user(User.builder().email("test").build()).build()
        when:
            Trader.create(trader)
        then:
            def apiException = thrown(ApiException)
            apiException.apiError != null

            apiException.apiError.title == "Validation Failed"
            apiException.apiError.status == 422
            apiException.apiError.detail == "The content you've sent contains 1 validation errors."
            apiException.apiError.traceId != null
            apiException.apiError.errors.size() == 1 as Integer
            def error = apiException.apiError.errors.iterator().next()
            error.message == "Email format is wrong"
    }

    def "can create trader"() {
        given:
            def trader = Trader.builder().
                    user(User.builder().email(UUID.randomUUID().toString() + "@gmail.com").password("Password123").build()).
                    build()
        when:
            def newTrader = Trader.create(trader)
        then:
            newTrader != null
            newTrader.id != null
    }

    def "can create trader with partner Id"() {
        given:
            def trader = Trader.builder().partnerId("YORDEX").
                    user(User.builder().email(UUID.randomUUID().toString() + "@gmail.com").password("Password123").build()).
                    build()
        when:
            def newTrader = Trader.create(trader)
        then:
            newTrader != null
            newTrader.id != null
            newTrader.connectionApikey != null
    }

    def "can create trader with partner id"() {
        given:
            def trader = Trader.builder().
                    user(User.builder().email(UUID.randomUUID().toString() + "@gmail.com").build()).partnerId("partner1").
                    build()
        when:
            def newTrader = Trader.create(trader)
        then:
            newTrader != null
            newTrader.id != null
    }
}
