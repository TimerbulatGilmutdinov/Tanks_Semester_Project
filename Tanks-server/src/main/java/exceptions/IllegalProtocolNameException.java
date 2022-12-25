package exceptions;

public class IllegalProtocolNameException extends Exception{
    public IllegalProtocolNameException() {
    }

    public IllegalProtocolNameException(String s) {
        super(s);
    }

    public IllegalProtocolNameException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public IllegalProtocolNameException(Throwable throwable) {
        super(throwable);
    }
}
