package superserfer.linker.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(value = {"password"},allowSetters = true)
public class User {

    @Id
    private String id;

    private String username;

    private String email;

    private List<Collection> collections;

    @JsonIgnoreProperties("password")
    private String password;

    public User(String username, String email, List<Collection> collections, String password) {
        this.username = username;
        this.email = email;
        this.collections = collections;
        this.password = password;
    }

    public User() {
        collections = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, username='%s', email='%s']",
                id,username,email);
    }
}
