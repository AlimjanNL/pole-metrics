package nl.alimjan.polemetrics.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import nl.alimjan.polemetrics.dto.LocationDTO;
import nl.alimjan.polemetrics.dto.MeterValueDTO;
import nl.alimjan.polemetrics.dto.ReportDTO;
import nl.alimjan.polemetrics.service.PoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PoleController.class)
public class PoleControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PoleService poleService;

  @Test
  public void getLocationsTest() throws Exception {
    List<LocationDTO> locations = new ArrayList<>();
    locations.add(new LocationDTO());

    given(poleService.getLocations(0, 5)).willReturn(locations);

    mockMvc.perform(get("/api/v1/locations")
            .param("page", "0")
            .param("size", "5")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
  }

  @Test
  public void getMeterValuesTest() throws Exception {
    List<MeterValueDTO> meterValues = new ArrayList<>();
    meterValues.add(new MeterValueDTO());
    given(poleService.getMeterValues(0, 10)).willReturn(meterValues);

    mockMvc.perform(get("/api/v1/meter-values")
            .param("page", "0")
            .param("size", "10")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
  }

  @Test
  public void getReportsTest() throws Exception {
    List<ReportDTO> reports = new ArrayList<>();
    reports.add(ReportDTO.builder().build());

    given(poleService.getReports(0, 1)).willReturn(reports);

    mockMvc.perform(get("/api/v1/reposts")
            .param("page", "0")
            .param("size", "1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
  }
}
