package in.ikcon.ims.services.impl;

import in.ikcon.ims.dtos.TicketDTO;
import in.ikcon.ims.entities.Employees;
import in.ikcon.ims.entities.Tickets;
import in.ikcon.ims.entities.Users;
import in.ikcon.ims.mapper.TicketMapper;
import in.ikcon.ims.repository.TicketRepository;
import in.ikcon.ims.services.EmployeeService;
import in.ikcon.ims.services.TicketService;
import in.ikcon.ims.services.UserService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final EmployeeService employeeService;
    private final UserService userService;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             EmployeeService employeeService, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.employeeService = employeeService;
        this.userService = userService;
    }

    @Override
    public void save(Map<String, String> ticketRequest) {
        Tickets tickets =new Tickets();
        tickets.setTitle(ticketRequest.get("title"));
        tickets.setDescription(ticketRequest.get("description"));
        tickets.setApprover(employeeService.getEmployeeInternal(ticketRequest.get("department")));
        tickets.setCreator(userService.getUserInternal(ticketRequest.get("entityName")));
        tickets.setTicketNo(generateRandom());
        ticketRepository.save(tickets);
    }

    @Override
    public List<TicketDTO> getUserTickets(String entityName) {
        Users users = userService.getUserInternal(entityName);
        return ticketRepository.findByCreator(users).stream()
                .map(TicketMapper::map).collect(Collectors.toList());
    }

    @Override
    public List<TicketDTO> getEmployeeTickets(String email) {
        Employees employees = employeeService.getEmployeeEntity(email);
        return ticketRepository.findByApprover(employees).stream()
                .map(TicketMapper::map).collect(Collectors.toList());
    }

    private String generateRandom() {
        String chars = "0123456789";
        int length = 8; // Change this as needed
        StringBuilder sb = new StringBuilder();
        sb.append("INC");
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}
