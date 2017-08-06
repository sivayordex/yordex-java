package com.yordex.http

import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import com.yordex.Yordex
import com.yordex.error.ApiException
import com.yordex.error.ClientException
import com.yordex.model.ApiError

final class DefaultRestTemplate implements YordexRestTemplate {

    @Override
    <T> T doPost(String url, T body, Class<T> responseClass, RequestOptions options, Params params) throws ApiException, ClientException {
        post(url, body, responseClass, options, params)
    }

    @Override
    <T> T doGet(String url, Class<T> responseClass, RequestOptions options, Params params) throws ApiException, ClientException {

        options = getOptions(options)

        def pathParams = params.pathParams
        def queryParams = params.queryParams

        def getRequest = Unirest.get(url)

        if (pathParams != null && pathParams.size() > 0) {
            pathParams.each {k, v -> getRequest.routeParam(k, v)}
        }

        if (queryParams != null && queryParams.size() > 0) {
            getRequest.queryString(queryParams)
        }

        def httpResponse = getRequest.headers(buildHeaders(options)).asObject(responseClass)

        return handleResponse(httpResponse)
    }

    private <T> T post(String url, T body, Class<T> responseClass, RequestOptions options, Params params)
            throws ApiException, ClientException {
        options = getOptions(options)

        HttpResponse<T> httpResponse

        def pathParams = params.pathParams
        def queryParams = params.queryParams

        def httpRequestWithBody = Unirest.post(url)

        if (pathParams != null && pathParams.size() > 0) {
            pathParams.each {k, v -> httpRequestWithBody.routeParam(k, v)}
        }

        if (queryParams != null && queryParams.size() > 0) {
            httpRequestWithBody.queryString(queryParams)
        }

        httpResponse = httpRequestWithBody.headers(buildHeaders(options)).body(body).asObject(responseClass)
        return handleResponse(httpResponse)
    }

    private static <T> T handleResponse(HttpResponse<T> httpResponse) {
        def statusCode = httpResponse.status

        if (isSuccessFul(statusCode)) {
            ApiError apiError = RestResource.OBJECT_MAPPER.readValue(httpResponse.getRawBody(), ApiError.class)
            apiError.traceId = httpResponse.getHeaders().getFirst("X-yordex-trace-id")
            throw new ApiException(apiError)
        }

        return httpResponse.body
    }

    private static RequestOptions getOptions(RequestOptions options) {
        if (options == null) {
            options = RequestOptions.defaults()
        }

        String apiKey = options.getApiKey()
        if ((apiKey == null || apiKey.trim().isEmpty()) && options.secured) {
            throw new ClientException("No Api Key found. Set your API key using 'Yordex.apiKey = <YOUR-API-KEY>'.")
        }

        return options
    }

    private static Boolean isSuccessFul(Integer statusCode) {
        statusCode < 200 || statusCode >= 300
    }

    private static String createQuery(Map<String, Object> params) throws UnsupportedEncodingException, ClientException {
        return RestResource.urlEncodeString(params.collect {k, v -> "$k=$v"}.join('&'))
    }

    private static Map<String, String> buildHeaders(RequestOptions options) {
        Map<String, String> headers = ['Accept-Charset': RestResource.CHARSET, 'User-Agent': "Yordex-Java-SDK/$Yordex.VERSION".toString()]

        if (options.secured) {
            headers['Authorization'] = options.apiKey
        }

        // debug headers
        String[] propertyNames = ['os.name', 'os.version', 'os.arch',
                                  'java.version', 'java.vendor', 'java.vm.version',
                                  'java.vm.vendor']
        Map<String, String> propertyMap = [:]
        for (String propertyName : propertyNames) {
            propertyMap.put(propertyName, System.getProperty(propertyName))
        }
        propertyMap['sdk.version'] = Yordex.VERSION
        propertyMap['lang'] = 'Java'
        propertyMap['publisher'] = 'Yordex'

        headers['X-Yordex-Client-User-Agent'] = RestResource.OBJECT_MAPPER.writeValueAsString(propertyMap)
        return headers
    }
}
