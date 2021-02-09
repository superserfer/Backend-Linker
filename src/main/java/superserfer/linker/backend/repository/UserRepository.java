package superserfer.linker.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import superserfer.linker.backend.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{username:?0}")
    User findByUsername(String username);

    @Query(value = "{email:?0}")
    User findByEmail(String email);
}
