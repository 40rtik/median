package de.kremer.median.dto;

import lombok.Data;

import java.util.List;

/**
 * Wrapperclass for a row.
 *
 * @author kremer
 */
@Data
public class RowDto {

    /*
    Columnames
     */
    private List<String> header;

    /*
    Records for each cell in the row.
     */
    private List<Record> records;

    /*
    Cell types in the row.
     */
    private List<String> typelist;

    public RowDto(List<String> header, List<Record> records, List<String> typelist) {
        this.header = header;
        this.records = records;
        this.typelist = typelist;
    }
}
