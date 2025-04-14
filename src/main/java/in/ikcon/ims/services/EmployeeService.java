package in.ikcon.ims.services;

import in.ikcon.ims.dtos.EmployeeDTO;
import in.ikcon.ims.entities.Employees;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface EmployeeService extends UserDetailsService {

    EmployeeDTO getEmployee(String email);
    List<EmployeeDTO> getEmployees();
    void save(Map<String,String> employeeRequest);
    Employees getEmployeeInternal(String department);
    Employees getEmployeeEntity(String email);
}
