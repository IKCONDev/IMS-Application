package in.ikcon.ims.services.impl;

import in.ikcon.ims.dtos.UserDTO;
import in.ikcon.ims.entities.Users;
import in.ikcon.ims.enums.UserType;
import in.ikcon.ims.mapper.UserMapper;
import in.ikcon.ims.repository.UserRepository;
import in.ikcon.ims.services.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO getUser(String entityName) {
        Users existingEntity = userRepository.findByEntityName(entityName)
                .orElseThrow(() -> new RuntimeException("user does not exists with entity:"+entityName));
        return UserMapper.map(existingEntity);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream().map(UserMapper::map).collect(Collectors.toList());
    }

    @Override
    public void save(Map<String, String> userRequest) {
        Users newUsers = new Users();
        newUsers.setUserType(UserType.valueOf(userRequest.get("userType")));
        newUsers.setEmail(userRequest.get("email"));
        newUsers.setPassword(passwordEncoder.encode(userRequest.get("password")));
        newUsers.setEntityName(userRequest.get("entityName"));
        userRepository.save(newUsers);
    }

    @Override
    public Users getUserInternal(String entityName) {
        return userRepository.findByEntityName(entityName)
                .orElseThrow(() -> new RuntimeException("user does not exists with entity:"+entityName));
    }

    @Override
    public Users getUserByEmail(String username) {
        return userRepository.findByEmail(username)
                .orElse(new Users());
    }
}
