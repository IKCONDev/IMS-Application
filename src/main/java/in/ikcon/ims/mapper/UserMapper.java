package in.ikcon.ims.mapper;

import in.ikcon.ims.dtos.UserDTO;
import in.ikcon.ims.entities.Users;

public class UserMapper {

    public static UserDTO map(Users users) {
        return UserDTO.builder()
                .email(users.getEmail())
                .entityName(users.getEntityName())
                .userType(users.getUserType()).build();
    }
}
