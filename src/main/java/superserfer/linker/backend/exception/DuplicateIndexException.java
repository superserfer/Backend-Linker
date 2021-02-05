package superserfer.linker.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DuplicateIndexException extends ResponseStatusException {

    public DuplicateIndexException(String message) {
        super(HttpStatus.CONFLICT,message);
    }
}
