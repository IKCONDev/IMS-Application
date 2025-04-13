package in.ikcon.ims.mapper;

import in.ikcon.ims.dtos.EmployeeDTO;
import in.ikcon.ims.entities.Employees;

public class EmployeeMapper {

    public static EmployeeDTO map(Employees employee) {
        return EmployeeDTO.builder()
                .email(employee.getEmail())
                .lastName(employee.getLastName())
                .department(DepartmentMapper.map(employee.getDepartments()))
                .firstName(employee.getFirstName()).build();
    }
}
