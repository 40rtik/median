package de.kremer.median.service;

import de.kremer.median.dto.Record;
import de.kremer.median.dto.RowDto;
import de.kremer.median.dto.TextRecordDto;
import de.kremer.median.exception.MedianException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MedianService {

    /**
     * Calculate the median for each label.
     *
     * @param input
     * @return List of Row's with one row per label with the medians for the columns.
     * @throws MedianException Thrown at null value or processing an empty cell/cell label.
     */
    public List<RowDto> calculateMedian(List<RowDto> input) throws MedianException {
        if(input == null){
            throw new MedianException("No input to process.");
        }
        List<RowDto> result = new ArrayList<>();
        if (input.size() > 0) {
            int columnNumber = input.get(0).getHeader().size();

            Set<String> labels = new HashSet<>();
            int labelIndex = input.get(0).getHeader().size() - 1;
            for (int i = 0; i < input.size(); i++) {
                RowDto row = input.get(i);
                labels.add((String) row.getRecords().get(labelIndex).getValue());
            }

            for (String label : labels) {
                List<RowDto> temp = new ArrayList<>();
                for (int i = 0; i < input.size(); i++) {
                    RowDto row = input.get(i);
                    if (row.getRecords().get(columnNumber - 1).getValue().equals(label)) {
                        temp.add(row);
                    }
                }
                RowDto median = new RowDto(input.get(0).getHeader(), new ArrayList<>(), new ArrayList<>());
                for (int i = 0; i < input.get(0).getHeader().size() - 1; i++) {
                    List<Record> numericMedian = new ArrayList<>();
                    List<Record> textMedian = new ArrayList<>();
                    for (int j = 0; j < temp.size(); j++) {
                        RowDto row = temp.get(j);
                        if (row.getRecords().get(i).getType().equals(Double.class.toString())) {
                            numericMedian.add(row.getRecords().get(i));
                        } else {
                            textMedian.add(row.getRecords().get(i));
                        }
                    }
                    if (numericMedian.size() > 0) {
                        numericMedian.sort((l1, l2) -> ((Double) l1.getValue()).compareTo((Double) l2.getValue()));
                        median.getRecords().add(numericMedian.get(numericMedian.size() / 2));
                        median.getTypelist().add(numericMedian.get(numericMedian.size() / 2).getType());

                    } else if (textMedian.size() > 0) {
                        textMedian.sort((l1, l2) -> ((String) l1.getValue()).compareTo((String) l2.getValue()));
                        median.getRecords().add(textMedian.get(textMedian.size() / 2));
                        median.getTypelist().add(textMedian.get(textMedian.size() / 2).getType());
                    }
                }
                if (temp.size() > 0) {
                    TextRecordDto tr = new TextRecordDto();
                    tr.setValue(label);
                    median.getRecords().add(tr);
                    median.getTypelist().add(tr.getType());
                }
                result.add(median);
            }
        }
        return result;
    }
}
