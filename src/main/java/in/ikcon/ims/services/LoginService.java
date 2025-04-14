package in.ikcon.ims.services;

import in.ikcon.ims.dtos.AuthRequest;
import in.ikcon.ims.entities.Employees;
import in.ikcon.ims.entities.Users;
import in.ikcon.ims.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final EmployeeService employeeService;
    private final JwtUtil jwtUtil;

    public LoginService(PasswordEncoder passwordEncoder, UserService userService, EmployeeService employeeService, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.employeeService = employeeService;
        this.jwtUtil = jwtUtil;
    }

    public String login(AuthRequest authRequest) {

        Users users = userService.getUserByEmail(authRequest.getUsername());
        if (users.getEmail() == null) {
            Employees employees = employeeService.getEmployeeEntity(authRequest.getUsername());
            if (employees!=null) {
                String token = jwtUtil.generateToken(employees.getEmail(),"EMP");
            }
        }
        return jwtUtil.generateToken(users.getEmail(),"BANK");
    }
}
