package bbc.gameoflifestub;

public class FailedEvolutionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FailedEvolutionException() {
        super();
    }

    public FailedEvolutionException(String message) {
        super(message);
    }

    public FailedEvolutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedEvolutionException(Throwable cause) {
        super(cause);
    }

    protected FailedEvolutionException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
