package com.vmoscalciuc.service;

import com.vmoscalciuc.dto.RegistrationResponse;
import com.vmoscalciuc.enums.Role;
import com.vmoscalciuc.exception.AuthenticationException;
import com.vmoscalciuc.exception.UserAlreadyExistsException;
import com.vmoscalciuc.model.UserEntity;
import com.vmoscalciuc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public RegistrationResponse registerUser(String username, String password) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if(userEntity.isPresent()){
            throw new UserAlreadyExistsException("User with this username already exists");
        }
        UserEntity newUser = new UserEntity();
                    newUser.setUsername(username);
                    newUser.setPassword(passwordEncoder.encode(password));
                    newUser.setRole(Role.MANAGER);
                    return modelMapper.map(userRepository.save(newUser),RegistrationResponse.class);
    }

    public RegistrationResponse authenticateUser(String username, String password) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if (userEntity.isPresent() && passwordEncoder.matches(password, userEntity.get().getPassword())) {
            return modelMapper.map(userEntity,RegistrationResponse.class);
        } else {
            throw new AuthenticationException("Cannot auth, username or password are incorrect");
        }
    }
}