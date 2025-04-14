package in.ikcon.ims.services.impl;

import in.ikcon.ims.dtos.EmployeeDTO;
import in.ikcon.ims.entities.Employees;
import in.ikcon.ims.mapper.EmployeeMapper;
import in.ikcon.ims.repository.EmployeeRepository;
import in.ikcon.ims.services.DepartmentService;
import in.ikcon.ims.services.EmployeeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentService departmentService, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EmployeeDTO getEmployee(String email) {
        Employees employees = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No employee found with email:"+email));
        return EmployeeMapper.map(employees);
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        return employeeRepository.findAll()
                .stream().map(EmployeeMapper::map).collect(Collectors.toList());
    }

    @Override
    public void save(Map<String, String> employeeRequest) {
        Employees newEmployee = new Employees();
        newEmployee.setFirstName(employeeRequest.get("firstName"));
        newEmployee.setLastName(employeeRequest.get("lastName"));
        newEmployee.setEmail(employeeRequest.get("email"));
        newEmployee.setPassword(passwordEncoder.encode(employeeRequest.get("password")));
        newEmployee.setDepartments(departmentService.getDepartmentInternal(employeeRequest.get("department")));
        employeeRepository.save(newEmployee);
    }

    @Override
    public Employees getEmployeeInternal(String department) {
       List<Employees> employeesList =  employeeRepository.findByDepartments_Name(department)
                .orElseThrow(() -> new RuntimeException("No employee found with department:"+department));

        if (employeesList.size() == 1) {
            return employeesList.get(0);
        }
        Random random = new Random();
        int index = random.nextInt(employeesList.size());
        return employeesList.get(index);
    }

    @Override
    public Employees getEmployeeEntity(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No employee found with email:"+email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employees> employees = employeeRepository.findByEmail(username);
        return (UserDetails) employees.get();
    }
}
