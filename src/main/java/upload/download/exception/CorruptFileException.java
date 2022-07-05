package upload.download.exception;

public class CorruptFileException extends RuntimeException{
    public CorruptFileException() {
        super();
    }

    public CorruptFileException(String message) {
        super(message);
    }

    public CorruptFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CorruptFileException(Throwable cause) {
        super(cause);
    }
}
