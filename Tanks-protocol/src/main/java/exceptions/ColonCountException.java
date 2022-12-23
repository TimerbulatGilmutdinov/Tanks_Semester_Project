package exceptions;

public class ColonCountException extends Exception {
    public ColonCountException() {
    }

    public ColonCountException(String s) {
        super(s);
    }

    public ColonCountException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ColonCountException(Throwable throwable) {
        super(throwable);
    }

}
