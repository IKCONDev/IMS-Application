package in.ikcon.ims.services;

import in.ikcon.ims.dtos.UserDTO;
import in.ikcon.ims.entities.Users;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO getUser(String entityName);

    List<UserDTO> getUsers();

    void save(Map<String,String> userRequest);

    Users getUserInternal(String entityName);
}
