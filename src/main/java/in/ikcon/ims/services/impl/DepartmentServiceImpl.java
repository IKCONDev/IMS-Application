package in.ikcon.ims.services.impl;

import in.ikcon.ims.dtos.DepartmentDTO;
import in.ikcon.ims.entities.Departments;
import in.ikcon.ims.repository.DepartmentRepository;
import in.ikcon.ims.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO getDepartment(String departmentName) {
        Departments departments = departmentRepository.findByName(departmentName)
                .orElseThrow(() -> new RuntimeException("No department found with name:"+departmentName));
        return DepartmentDTO.builder()
                .name(departments.getName()).description(departments.getDescription()).build();
    }

    @Override
    public List<DepartmentDTO> getALLDepartments() {
        List<Departments> departmentsList = departmentRepository.findAll();
        return departmentsList.stream()
                .map(departments -> DepartmentDTO.builder().build()).collect(Collectors.toList());
    }

    @Override
    public void save(Map<String, String> departmentRequest) {
        String name = departmentRequest.get("name");
        String description = departmentRequest.get("description");
        Departments departments = new Departments();
        departments.setName(name);
        departments.setDescription(description);
        departmentRepository.save(departments);
    }

    @Override
    public Departments getDepartmentInternal(String departmentName) {
        return departmentRepository.findByName(departmentName)
                .orElseThrow(() -> new RuntimeException("No department found with name:"+departmentName));
    }
}
