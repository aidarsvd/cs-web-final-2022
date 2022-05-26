package pro.aidar.alatoonews.model.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.aidar.alatoonews.model.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String name);
}