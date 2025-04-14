package in.ikcon.ims.controller;

import in.ikcon.ims.dtos.TicketDTO;
import in.ikcon.ims.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/employee" , produces = {"application/json"})
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/save", consumes = {"application/json"})
    public ResponseEntity<?> save(@RequestBody Map<String,String> employeeRequest) {
        employeeService.save(employeeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getEmployee(@RequestParam(required = false) String email) {
        if (StringUtils.hasText(email)) {
            return new ResponseEntity<>(employeeService.getEmployee(email),HttpStatus.OK);
        }
        return new ResponseEntity<>(employeeService.getEmployees(),HttpStatus.OK);
    }
}