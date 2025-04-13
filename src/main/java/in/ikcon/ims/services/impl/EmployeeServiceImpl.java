package in.ikcon.ims.services.impl;

import in.ikcon.ims.dtos.EmployeeDTO;
import in.ikcon.ims.entities.Employees;
import in.ikcon.ims.mapper.EmployeeMapper;
import in.ikcon.ims.repository.EmployeeRepository;
import in.ikcon.ims.services.DepartmentService;
import in.ikcon.ims.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
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
        newEmployee.setPassword(employeeRequest.get("password"));
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
}
