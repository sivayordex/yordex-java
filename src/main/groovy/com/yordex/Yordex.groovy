package com.yordex

abstract class Yordex {
    private final static int DEFAULT_CONNECTION_REQUEST_TIMEOUT = 10 * 1000

    private final static int DEFAULT_CONNECTION_TIMEOUT = 10 * 1000

    private final static int DEFAULT_MAX_ROUTE_PER_CONNECTION = 30

    private final static int DEFAULT_MAX_CONNECTIONS = 200

    private final static int DEFAULT_VALIDATE_AFTER = 10 * 1000

    private final static int DEFAULT_SOCKET_TIMEOUT = 60 * 1000

    static final String LIVE_API_BASE_URL = "https://api.yordex.com"

    static final String VERSION = "1.0.0"

    static volatile String apiKey

    private static volatile int connectionRequestTimeout = -1

    private static volatile int connectionTimeout = -1

    private static volatile int maxConnections = -1

    private static volatile int maxRoutePerConnection = DEFAULT_MAX_ROUTE_PER_CONNECTION

    private static volatile int validateAfter = DEFAULT_VALIDATE_AFTER

    private static volatile int socketTimeOut = DEFAULT_SOCKET_TIMEOUT

    private static volatile String apiBaseUrl = LIVE_API_BASE_URL

    static int getConnectionRequestTimeout() {
        if (connectionRequestTimeout == -1 as Integer) {
            return DEFAULT_CONNECTION_REQUEST_TIMEOUT
        }
        return connectionRequestTimeout
    }

    static int getConnectionTimeout() {
        if (connectionTimeout == -1 as Integer) {
            return DEFAULT_CONNECTION_TIMEOUT
        }
        return connectionTimeout
    }

    static int getMaxConnections() {
        if (maxConnections == -1 as Integer) {
            return DEFAULT_MAX_CONNECTIONS
        }
        return maxConnections
    }

    static String getApiBaseUrl() {
        return apiBaseUrl
    }

    static String getApiKey() {
        return apiKey
    }

    static int getMaxRoutePerConnection() {
        return maxRoutePerConnection
    }

    static int getValidateAfter() {
        return validateAfter
    }

    static int getSocketTimeOut() {
        return socketTimeOut
    }

    static void setApiKey(String apiKey) {
        this.@apiKey = apiKey
    }

    static void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.@connectionRequestTimeout = connectionRequestTimeout
    }

    static void setConnectionTimeout(int connectionTimeout) {
        this.@connectionTimeout = connectionTimeout
    }

    static void setMaxConnections(int maxConnections) {
        this.@maxConnections = maxConnections
    }

    /**
     * This is for testing only
     */
    static void setApiBaseUrl(String apiBaseUrl) {
        this.@apiBaseUrl = apiBaseUrl
    }
}
