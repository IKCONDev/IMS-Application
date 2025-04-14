package in.ikcon.ims.services;

import in.ikcon.ims.dtos.TicketDTO;

import java.util.List;
import java.util.Map;

public interface TicketService {

    void save(Map<String,String> ticketRequest);

    List<TicketDTO> getUserTickets(String entityName);

    List<TicketDTO> getEmployeeTickets(String email);
}
