package superserfer.linker.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonWebToken {
    private String jwt;

    public JsonWebToken(String jwt) {
        this.jwt = jwt;
    }
}
