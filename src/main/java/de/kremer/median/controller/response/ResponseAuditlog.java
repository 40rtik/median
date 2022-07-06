package de.kremer.median.controller.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ResponseAuditlog {

    private Date date;
    private List<String> labels;

    public ResponseAuditlog(Date date, List<String> labels) {
        if (labels == null) {
            this.labels = new ArrayList<>();
        } else {
            this.labels = labels;
        }
        this.date = date;
    }
}
