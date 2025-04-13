package in.ikcon.ims.repository;

import in.ikcon.ims.entities.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Departments,Long> {

    Optional<Departments> findByName(String departmentName);
}
