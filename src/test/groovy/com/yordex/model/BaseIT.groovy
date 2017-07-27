package com.yordex.model

import com.yordex.Yordex
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import spock.lang.Specification

class BaseIT extends Specification {

    def setup() {
        RestAssured.port = 80
        Yordex.apiBaseUrl = "http://api.dev.yordex.com"
        RestAssured.baseURI = Yordex.apiBaseUrl
        RestAssured.requestSpecification = new RequestSpecBuilder().addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .log(LogDetail.ALL)
                .build()
    }
}
