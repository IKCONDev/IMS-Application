package in.ikcon.ims.controller;

import in.ikcon.ims.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping(path = "/api/v1/user" , produces = {"application/json"})
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/save" , consumes = {"application/json"})
    public ResponseEntity<?> save(@RequestBody Map<String,String> userRequest) {
        //log.info("save department() entered with body : {}",userRequest);
        userService.save(userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getEntity(@RequestParam(required = false) String entityName) {
        if (StringUtils.hasText(entityName)) {
            return new ResponseEntity<>(userService.getUser(entityName),HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
    }
}