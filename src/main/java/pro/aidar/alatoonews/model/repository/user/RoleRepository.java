package pro.aidar.alatoonews.model.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.aidar.alatoonews.model.entity.user.Role;
import pro.aidar.alatoonews.model.entity.user.Roles;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
