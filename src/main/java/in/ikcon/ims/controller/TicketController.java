package in.ikcon.ims.controller;

import in.ikcon.ims.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/ticket" , produces = {"application/json"})
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(path = "/save" , consumes = {"application/json"})
    public ResponseEntity<?> save(@RequestBody Map<String,String> ticketRequest){
        ticketService.save(ticketRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/user/{entityName}")
    public ResponseEntity<?> getUserTickets(@PathVariable String entityName){
        return new ResponseEntity<>(ticketService.getUserTickets(entityName),HttpStatus.OK);
    }

    @GetMapping(path = "/employee/{email}")
    public ResponseEntity<?> getEmployeeTickets(@PathVariable String email) {
        return new ResponseEntity<>(ticketService.getEmployeeTickets(email),HttpStatus.OK);
    }
}
