package de.kremer.median.controller;

import de.kremer.median.controller.response.RequestMedian;
import de.kremer.median.controller.response.ResponseMedian;
import de.kremer.median.dto.RowDto;
import de.kremer.median.exception.MedianException;
import de.kremer.median.service.AuditlogService;
import de.kremer.median.service.MedianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Rest service for the median calculation.
 *
 * @author kremer
 */
@RestController
public class MedianController {

    @Autowired
    private AuditlogService auditlogService;

    @Autowired
    private MedianService medianService;

    @PostMapping(value = "/median", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMedian median(@Valid @RequestBody RequestMedian requestMedian) throws MedianException {
        List<RowDto> rowDtos = new ArrayList<>();
        for (de.kremer.median.controller.response.Row row : requestMedian.getRows()) {
            RowDto rowDto = new RowDto(row.getHeader(), row.getRecords(), row.getTypelist());
            rowDtos.add(rowDto);
        }

        List<RowDto> calculateMedian = medianService.calculateMedian(rowDtos);
        auditlogService.bookmark(calculateMedian);

        List<de.kremer.median.controller.response.Row> requestRows = new ArrayList<>();
        for (RowDto row : calculateMedian) {
            de.kremer.median.controller.response.Row requestRow = new de.kremer.median.controller.response.Row(row.getHeader(), row.getRecords(), row.getTypelist());
            requestRows.add(requestRow);
        }
        return new ResponseMedian(requestRows);
    }
}
