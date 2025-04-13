package in.ikcon.ims.repository;

import in.ikcon.ims.entities.Employees;
import in.ikcon.ims.entities.Tickets;
import in.ikcon.ims.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Tickets,Long> {

    List<Tickets> findByCreator(Users users);
    List<Tickets> findByApprover(Employees employees);
}
