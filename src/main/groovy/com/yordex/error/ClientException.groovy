package com.yordex.error

class ClientException extends Exception {

    ClientException(String message) {
        super(message)
    }

    ClientException(Throwable ex) {
        super(ex)
    }
}
