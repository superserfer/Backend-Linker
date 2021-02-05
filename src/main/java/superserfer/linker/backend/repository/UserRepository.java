package superserfer.linker.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import superserfer.linker.backend.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByUsername(String username);
    public User findByEmail(String email);
}
