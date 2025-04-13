package in.ikcon.ims.mapper;

import in.ikcon.ims.dtos.DepartmentDTO;
import in.ikcon.ims.entities.Departments;

public class DepartmentMapper {

    public static DepartmentDTO map(Departments department) {
        return DepartmentDTO.builder()
                .description(department.getDescription())
                .name(department.getName()).build();
    }
}
