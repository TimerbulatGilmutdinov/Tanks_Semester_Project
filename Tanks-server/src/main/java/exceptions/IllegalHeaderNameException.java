package exceptions;

public class IllegalHeaderNameException extends Exception {
    public IllegalHeaderNameException() {
    }

    public IllegalHeaderNameException(String s) {
        super(s);
    }

    public IllegalHeaderNameException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public IllegalHeaderNameException(Throwable throwable) {
        super(throwable);
    }
}
