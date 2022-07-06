package de.kremer.median.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.kremer.median.controller.response.RequestMedian;
import de.kremer.median.controller.response.Row;
import de.kremer.median.dto.NumericalRecordDto;
import de.kremer.median.dto.Record;
import de.kremer.median.dto.RowDto;
import de.kremer.median.dto.TextRecordDto;
import de.kremer.median.service.AuditlogService;
import de.kremer.median.service.MedianService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MedianController.class)
public class MedianControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MedianService medianService;

    @MockBean
    private AuditlogService auditlogService;

    @Test
    public void validCsvInput()
            throws Exception {

        List<Row> calculatedMedians = new ArrayList<>();
        calculatedMedians.add(createRow());
        calculatedMedians.add(createRow());
        calculatedMedians.add(createRow());
        RequestMedian requestMedian = new RequestMedian(calculatedMedians);

        List<RowDto> innerRowList = new ArrayList<>();
        innerRowList.add(createRowDto());
        innerRowList.add(createRowDto());
        innerRowList.add(createRowDto());

        given(medianService.calculateMedian(any())).willReturn(innerRowList);
        doNothing().when(auditlogService).bookmark(any());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(requestMedian);

        MvcResult response = mvc.perform(post("/median")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString).characterEncoding("utf-8"))
                .andExpect(status().isOk()).andReturn();

        assertTrue(response.getResponse().getStatus() == 200);

        verify(medianService, times(1)).calculateMedian(any());
        verify(auditlogService, times(1)).bookmark(any());
    }

    @Test
    public void invadlidCsvInput()
            throws Exception {

        List<Row> calculatedMedians = new ArrayList<>();
        calculatedMedians.add(createRow());
        calculatedMedians.add(createRow());
        calculatedMedians.add(createRow());
        RequestMedian requestMedian = new RequestMedian(null);

        List<RowDto> innerRowList = new ArrayList<>();
        innerRowList.add(createRowDto());
        innerRowList.add(createRowDto());
        innerRowList.add(createRowDto());

        given(medianService.calculateMedian(any())).willReturn(innerRowList);
        doNothing().when(auditlogService).bookmark(any());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(requestMedian);

        MvcResult response = mvc.perform(post("/median")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonString).characterEncoding("utf-8"))
                .andExpect(status().isBadRequest()).andReturn();

        assertEquals(response.getResponse().getStatus(), 400);
        assertNotNull(response.getResponse().getContentAsString());

        verify(medianService, times(0)).calculateMedian(any());
        verify(auditlogService, times(0)).bookmark(any());
    }

    private Row createRow() {
        List<String> headers = new ArrayList<>();
        headers.add("attr1");
        headers.add("attr2");
        headers.add("attr3");
        headers.add("attr4");
        headers.add("attr5");
        headers.add("label");

        List<String> types = new ArrayList<>();
        List<Record> records = new ArrayList<>();
        NumericalRecordDto numRec1 = new NumericalRecordDto();
        numRec1.setValue(0.1);
        records.add(numRec1);
        types.add(numRec1.getType());
        NumericalRecordDto numRec2 = new NumericalRecordDto();
        numRec2.setValue(0.1);
        records.add(numRec2);
        types.add(numRec2.getType());
        NumericalRecordDto numRec3 = new NumericalRecordDto();
        numRec3.setValue(0.1);
        records.add(numRec3);
        types.add(numRec3.getType());
        NumericalRecordDto numRec4 = new NumericalRecordDto();
        numRec4.setValue(0.1);
        records.add(numRec4);
        types.add(numRec4.getType());
        NumericalRecordDto numRec5 = new NumericalRecordDto();
        numRec5.setValue(0.1);
        records.add(numRec5);
        types.add(numRec5.getType());
        TextRecordDto textRec1 = new TextRecordDto();
        textRec1.setValue("one");
        records.add(textRec1);
        types.add(textRec1.getType());

        Row row = new Row(headers, records, types);
        return row;
    }

    private RowDto createRowDto() {
        List<String> headers = new ArrayList<>();
        headers.add("attr1");
        headers.add("attr2");
        headers.add("attr3");
        headers.add("attr4");
        headers.add("attr5");
        headers.add("label");

        List<String> types = new ArrayList<>();
        List<Record> records = new ArrayList<>();
        NumericalRecordDto numRec1 = new NumericalRecordDto();
        numRec1.setValue(0.1);
        records.add(numRec1);
        types.add(numRec1.getType());
        NumericalRecordDto numRec2 = new NumericalRecordDto();
        numRec2.setValue(0.1);
        records.add(numRec2);
        types.add(numRec2.getType());
        NumericalRecordDto numRec3 = new NumericalRecordDto();
        numRec3.setValue(0.1);
        records.add(numRec3);
        types.add(numRec3.getType());
        NumericalRecordDto numRec4 = new NumericalRecordDto();
        numRec4.setValue(0.1);
        records.add(numRec4);
        types.add(numRec4.getType());
        NumericalRecordDto numRec5 = new NumericalRecordDto();
        numRec5.setValue(0.1);
        records.add(numRec5);
        types.add(numRec5.getType());
        TextRecordDto textRec1 = new TextRecordDto();
        textRec1.setValue("one");
        records.add(textRec1);
        types.add(textRec1.getType());

        RowDto row = new RowDto(headers, records, types);
        return row;
    }
}