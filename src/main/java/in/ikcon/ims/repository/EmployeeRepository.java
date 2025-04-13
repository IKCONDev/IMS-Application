package in.ikcon.ims.repository;

import in.ikcon.ims.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employees,Long> {

    Optional<Employees> findByEmail(String email);
    Optional<List<Employees>> findByDepartments_Name(String departmentName);

}
