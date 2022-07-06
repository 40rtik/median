package de.kremer.median.service;

import de.kremer.median.dto.AuditlogDto;
import de.kremer.median.dto.RowDto;
import de.kremer.median.exception.MedianException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Can be replaced with another service to persist the processing information in a database.
 *
 * @author kremer
 */
@Service
public class AuditlogService {

    private List<AuditlogDto> history = new ArrayList<>();

    /**
     * Persist the calculated medians in an inner property.
     *
     * @param medians The calculated medians per label, each in one row.
     * @throws MedianException Thrown at null value or processing an empty cell.
     */
    public void bookmark(List<RowDto> medians) throws MedianException {
        if (medians == null) {
            throw new MedianException("No medians to persist.");
        } else {
            List<String> labels = new ArrayList<>();
            if (medians.size() > 0) {
                for (RowDto row : medians) {
                    if (row.getRecords() != null && row.getRecords().size() > 0) {
                        labels.add(row.getRecords().get(row.getRecords().size() - 1).getValue() + "");
                    }
                }
                this.history.add(new AuditlogDto(labels));
            }
        }
    }

    public List<AuditlogDto> getHistory() {
        return this.history;
    }
}
