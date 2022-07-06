package de.kremer.median.controller.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.kremer.median.dto.Record;
import de.kremer.median.serialization.RowDeserializer;
import lombok.Data;

import java.util.List;

@JsonDeserialize(using = RowDeserializer.class)
@Data
public class Row {

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

    public Row(List<String> header, List<Record> records, List<String> typelist) {
        this.header = header;
        this.records = records;
        this.typelist = typelist;
    }
}
