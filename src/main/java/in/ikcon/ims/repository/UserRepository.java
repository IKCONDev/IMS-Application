package in.ikcon.ims.repository;

import in.ikcon.ims.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByEntityName(String entityName);
}
