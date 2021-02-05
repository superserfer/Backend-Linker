package superserfer.linker.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
public class User {

    @Id
    public String id;

    public String username;
    public String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User() {
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, username='%s', email='%s']",
                id,username,email);
    }
}
