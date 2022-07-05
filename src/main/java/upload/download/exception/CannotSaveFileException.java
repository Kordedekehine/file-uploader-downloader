package upload.download.exception;

public class CannotSaveFileException extends RuntimeException{
    public CannotSaveFileException() {
        super();
    }

    public CannotSaveFileException(String message) {
        super(message);
    }

    public CannotSaveFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotSaveFileException(Throwable cause) {
        super(cause);
    }
}
