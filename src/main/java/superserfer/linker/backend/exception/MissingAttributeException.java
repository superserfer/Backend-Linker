package superserfer.linker.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MissingAttributeException extends ResponseStatusException {

    public MissingAttributeException(String message) {
        super(HttpStatus.BAD_REQUEST,message);
    }
}
