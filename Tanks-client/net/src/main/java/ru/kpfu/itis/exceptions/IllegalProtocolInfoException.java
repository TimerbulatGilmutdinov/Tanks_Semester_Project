package ru.kpfu.itis.exceptions;

public class IllegalProtocolInfoException extends Exception{
    public IllegalProtocolInfoException() {
    }

    public IllegalProtocolInfoException(String s) {
        super(s);
    }

    public IllegalProtocolInfoException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public IllegalProtocolInfoException(Throwable throwable) {
        super(throwable);
    }
}
