package com.example.share2save.model.error;

/**
 * Created by josh on 12/22/13.
 */
public class InvalidJSONException extends Exception {
    static final long serialVersionUID = -7034897190745766939L;

    /** Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public InvalidJSONException() {
        super();
    }

    public InvalidJSONException(String message) {
        super(message);
    }

    public InvalidJSONException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidJSONException(Throwable cause) {
        super(cause);
    }

}
