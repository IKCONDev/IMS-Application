package in.ikcon.ims.services;

import in.ikcon.ims.dtos.DepartmentDTO;
import in.ikcon.ims.entities.Departments;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    DepartmentDTO getDepartment(String departmentName);
    List<DepartmentDTO> getALLDepartments();
    void save(Map<String, String> departmentRequest);
    Departments getDepartmentInternal(String departmentName);
}
