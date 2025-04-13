package in.ikcon.ims.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import in.ikcon.ims.enums.UserType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private String email;
    private String password;
    private String entityName;
    private UserType userType;
}
