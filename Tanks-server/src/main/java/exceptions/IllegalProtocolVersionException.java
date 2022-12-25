package exceptions;

public class IllegalProtocolVersionException extends Exception{
    public IllegalProtocolVersionException() {
    }

    public IllegalProtocolVersionException(String s) {
        super(s);
    }

    public IllegalProtocolVersionException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public IllegalProtocolVersionException(Throwable throwable) {
        super(throwable);
    }
}
