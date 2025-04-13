package in.ikcon.ims.controller;

import in.ikcon.ims.dtos.DepartmentDTO;
import in.ikcon.ims.services.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/department")
public class DepartmentController {

    private final static Logger log = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(path = "/save" , produces = {"application/json"} , consumes = {"application/json"})
    public void saveDepartment(@RequestBody Map<String , String> departmentRequest) {
        log.info("save department() entered with body : {}",departmentRequest);
        departmentService.save(departmentRequest);
    }

    @GetMapping(path= "/{departmentName}",produces = {"application/json"})
    public ResponseEntity<?> getDepartments(@PathVariable String departmentName) {
        log.info("GET departments() entered");
        if (StringUtils.hasText(departmentName)) {
            log.info("with departmentName:{}",departmentName);
            DepartmentDTO departmentDTO = departmentService.getDepartment(departmentName);
            return new ResponseEntity<>(departmentDTO,HttpStatus.OK);
        }
        List<DepartmentDTO> departmentList = departmentService.getALLDepartments();
        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }
}
