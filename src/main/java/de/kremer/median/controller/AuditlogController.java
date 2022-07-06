package de.kremer.median.controller;

import de.kremer.median.controller.response.ResponseAuditlog;
import de.kremer.median.dto.AuditlogDto;
import de.kremer.median.exception.MedianException;
import de.kremer.median.service.AuditlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest service to retrieve the median processing history.
 *
 * @author kremer
 */
@RestController
public class AuditlogController {

    @Autowired
    private AuditlogService auditlogService;

    @CrossOrigin
    @GetMapping(value = "/auditlog", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ResponseAuditlog> getAuditlog() throws MedianException {
        List<ResponseAuditlog> response = new ArrayList<>();
        for(AuditlogDto auditlogDto: auditlogService.getHistory()){
            ResponseAuditlog responseAuditlog = new ResponseAuditlog(auditlogDto.getDate(), auditlogDto.getLabels());
            response.add(responseAuditlog);
        }
        return response;
    }
}
