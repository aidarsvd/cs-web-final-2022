package pro.aidar.alatoonews.model.service.user.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.aidar.alatoonews.model.dto.user.UserDto;
import pro.aidar.alatoonews.model.entity.user.Role;
import pro.aidar.alatoonews.model.entity.user.Roles;
import pro.aidar.alatoonews.model.entity.user.User;
import pro.aidar.alatoonews.model.repository.user.RoleRepository;
import pro.aidar.alatoonews.model.repository.user.UserRepository;
import pro.aidar.alatoonews.model.service.user.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        log.info("Saving {} to db", userDto.getUsername());
        User user = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .username(userDto.getUsername())
                .email(userDto.getEmail() + "@alatoo.edu.kg")
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(new ArrayList<>())
                .build();
        userRepository.save(user);
        assignRoleToUser(Roles.USER, userDto.getUsername());
    }

    @Override
    public User findBuUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with id={}", id);
        userRepository.deleteById(id);
    }

    @Override
    public boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void assignRoleToUser(Roles roles, String username) {
        Optional<Role> role = roleRepository.findByName(roles);
        User user = userRepository.findByUsername(username);
        if (user != null && role.isPresent()) {
            user.getRoles().clear();
            user.getRoles().add(role.get());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
