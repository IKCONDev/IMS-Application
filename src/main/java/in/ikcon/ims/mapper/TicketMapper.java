package in.ikcon.ims.mapper;

import in.ikcon.ims.dtos.TicketDTO;
import in.ikcon.ims.entities.Tickets;

public class TicketMapper {

    public static TicketDTO map(Tickets tickets) {
        return TicketDTO.builder()
                .title(tickets.getTitle())
                .ticketNo(tickets.getTicketNo())
                .description(tickets.getDescription())
                .creator(UserMapper.map(tickets.getCreator()))
                .approver(EmployeeMapper.map(tickets.getApprover()))
                .status(tickets.getStatus())
                .priority(tickets.getPriority())
                .build();
    }
}
