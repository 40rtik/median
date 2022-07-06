package de.kremer.median.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMedian {

    @NotNull
    List<Row> rows;

}
