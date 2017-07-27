package com.yordex.http

import com.yordex.error.ApiException
import com.yordex.error.ClientException

interface YordexRestTemplate {
    public <T> T doPost(String url, T body, Class<T> responseClass, RequestOptions options, Params params)
            throws ApiException, ClientException

    public <T> T doGet(String url, Class<T> responseClass, RequestOptions options, Params params) throws ApiException, ClientException
}
