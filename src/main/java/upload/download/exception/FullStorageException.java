package upload.download.exception;

public class FullStorageException extends RuntimeException{
    public FullStorageException() {
        super();
    }

    public FullStorageException(String message) {
        super(message);
    }

    public FullStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public FullStorageException(Throwable cause) {
        super(cause);
    }
}
