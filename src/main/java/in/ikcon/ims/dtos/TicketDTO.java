package in.ikcon.ims.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import in.ikcon.ims.enums.Priority;
import in.ikcon.ims.enums.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDTO {

    private String title;
    private String ticketNo;
    private String description;
    private UserDTO creator;
    private EmployeeDTO approver;
    private Priority priority;
    private Status status;
    private DepartmentDTO departmentDetails;
}
