package pro.aidar.alatoonews.model.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import pro.aidar.alatoonews.model.dto.user.UserDto;
import pro.aidar.alatoonews.model.entity.user.Roles;
import pro.aidar.alatoonews.model.entity.user.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    void saveUser(UserDto user);

    Optional<User> findById(Long id);

    User updateUser(User user);

    void deleteUser(Long id);

    boolean isUsernameExist(String username);

    boolean isEmailExist(String email);

    void assignRoleToUser(Roles roles, String username);
}
