package in.ikcon.ims.controller;

import in.ikcon.ims.dtos.AuthRequest;
import in.ikcon.ims.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        String token = loginService.login(authRequest);
        Map<String,String> response = new HashMap<>();
        response.put("token" , token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
