package de.kremer.median.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Representation for an auditlog, which keep the client request informations.
 *
 * @author kremer
 */
@Data
public class AuditlogDto {

    private Date date;
    private List<String> labels;

    public AuditlogDto(List<String> labels) {
        if (labels == null) {
            this.labels = new ArrayList<>();
        } else {
            this.labels = labels;
        }
        date = Calendar.getInstance().getTime();
    }
}
