package exceptions;

public class IllegalProtocolMethodException extends Exception {
    public IllegalProtocolMethodException() {
    }

    public IllegalProtocolMethodException(String s) {
        super(s);
    }

    public IllegalProtocolMethodException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public IllegalProtocolMethodException(Throwable throwable) {
        super(throwable);
    }
}
