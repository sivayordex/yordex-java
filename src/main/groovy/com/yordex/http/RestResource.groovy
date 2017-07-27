package com.yordex.http

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.mashape.unirest.http.ObjectMapper
import com.mashape.unirest.http.Unirest
import com.yordex.Yordex
import com.yordex.error.ApiException
import com.yordex.error.ClientException

abstract class RestResource {

    @JsonIgnore
    static final String CHARSET = "UTF-8"

    @JsonIgnore
    private static final DefaultRestTemplate defaultRestTemplate = new DefaultRestTemplate()

    public static final com.fasterxml.jackson.databind.ObjectMapper OBJECT_MAPPER = new com.fasterxml.jackson.databind.ObjectMapper().
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).
            setSerializationInclusion(JsonInclude.Include.NON_EMPTY)

    static {
        Unirest.setObjectMapper(new ObjectMapper() {

            @Override
            def <T> T readValue(String value, Class<T> valueType) {
                try {
                    return OBJECT_MAPPER.readValue(value, valueType)
                } catch (IOException ex) {
                    throw new ClientException(ex)
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return OBJECT_MAPPER.writeValueAsString(value)
                } catch (JsonProcessingException ex) {
                    throw new ClientException(ex)
                }
            }
        })

        Unirest.setConcurrency(Yordex.maxConnections, Yordex.maxRoutePerConnection)
        Unirest.setTimeouts(Yordex.connectionTimeout, Yordex.socketTimeOut)
        Unirest.setDefaultHeader("accept", "application/json")
        Unirest.setDefaultHeader("content-type", "application/json")
    }

    static String urlEncodeString(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null
        } else {
            return URLEncoder.encode(str, CHARSET)
        }
    }

    static <T> T postRequest(String resourcePath, T body, Class<T> responseClass, RequestOptions requestOptions)
            throws ApiException, ClientException {
        return defaultRestTemplate.doPost(Yordex.apiBaseUrl + resourcePath, body, responseClass, requestOptions, null)
    }

    static <T> T postRequest(String resourcePath, T body, Class<T> responseClass, RequestOptions requestOptions, Params params)
            throws ApiException, ClientException {
        return defaultRestTemplate.doPost(Yordex.apiBaseUrl + resourcePath, body, responseClass, requestOptions, params)
    }

    static <T> T getRequest(String resourcePath, Class<T> responseClass, RequestOptions requestOptions, Params params)
            throws ApiException, ClientException {
        return defaultRestTemplate.doGet(Yordex.apiBaseUrl + resourcePath, responseClass, requestOptions, params)
    }
}
