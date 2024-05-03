package nl.alimjan.polemetrics.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nl.alimjan.polemetrics.client.PoleClient;
import nl.alimjan.polemetrics.dto.LocationDTO;
import nl.alimjan.polemetrics.dto.MeterValueDTO;
import nl.alimjan.polemetrics.dto.ReportDTO;
import nl.alimjan.polemetrics.mapper.LocationMapper;
import nl.alimjan.polemetrics.model.Connector;
import nl.alimjan.polemetrics.model.EVSE;
import nl.alimjan.polemetrics.model.Location;
import nl.alimjan.polemetrics.model.MeterValue;
import nl.alimjan.polemetrics.repository.EnergyPriceRepository;
import nl.alimjan.polemetrics.repository.LocationRepository;
import nl.alimjan.polemetrics.repository.MeterValueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class PoleServiceImplTest {

  @Mock
  private PoleClient poleClient;

  @Mock
  private LocationRepository locationRepository;

  @Mock
  private MeterValueRepository meterValueRepository;

  @Mock
  private EnergyPriceRepository energyPriceRepository;

  @Mock
  private LocationMapper locationMapper;

  @InjectMocks
  private PoleServiceImpl poleService;

  @Test
  void testFetchData() {
    // Act
    poleService.fetchData();

    // Assert
    verify(locationRepository, times(1)).saveAll(anyList());
    verify(meterValueRepository, times(1)).saveAll(anyList());
    verify(energyPriceRepository, times(1)).saveAll(anyList());
  }


  @Test
  void testGetLocations() {
    // Arrange
    Location testLocation1 = new Location();
    testLocation1.setId("1");
    testLocation1.setName("testLocation one");

    Location testLocation2 = new Location();
    testLocation1.setId("2");
    testLocation1.setName("testLocation two");

    Page<Location> page = new PageImpl<>(Arrays.asList(testLocation1, testLocation2));
    when(locationRepository.findAll(any(PageRequest.class))).thenReturn(page);

    // Act
    List<LocationDTO> results = poleService.getLocations(0, 2);

    // Assert
    assertNotNull(results);
    assertEquals(2, results.size());

    verify(locationRepository).findAll(any(PageRequest.class));
  }

  @Test
  void testGetMeterValues() {
    // Arrange
    MeterValue testMeterValue1 = new MeterValue();
    testMeterValue1.setId(1L);
    testMeterValue1.setMeterValue(123.45);

    MeterValue testMeterValue2 = new MeterValue();
    testMeterValue2.setId(2L);
    testMeterValue2.setMeterValue(678.90);

    Page<MeterValue> page = new PageImpl<>(Arrays.asList(testMeterValue1, testMeterValue2));
    when(meterValueRepository.findAll(any(PageRequest.class))).thenReturn(page);

    // Act
    List<MeterValueDTO> results = poleService.getMeterValues(0, 2);

    // Assert
    assertNotNull(results);
    assertEquals(2, results.size());  // Validate the number of returned DTOs

    verify(meterValueRepository).findAll(any(PageRequest.class));
  }

  @Test
  void testGetReports() {
    // Arrange
    Location location = new Location();
    location.setName("Example Location");

    List<EVSE> evses = new ArrayList<>();
    EVSE evse1 = new EVSE();
    evse1.setUid("1");
    Connector connector1 = new Connector();
    connector1.setId("1");
    connector1.setPowerType("AC_3_PHASE");
    evse1.setConnectors(List.of(connector1));

    EVSE evse2 = new EVSE();
    evse2.setUid("2");
    Connector connector2 = new Connector();
    connector2.setId("2");
    connector2.setPowerType("AC_3_PHASE");
    evse2.setConnectors(List.of(connector2));

    evses.add(evse1);
    evses.add(evse2);
    location.setEvses(evses);

    Page<Location> locationsPage = new PageImpl<>(List.of(location));
    when(locationRepository.findAll(any(Pageable.class))).thenReturn(locationsPage);

    // Act
    List<ReportDTO> reports = poleService.getReports(0, 10);

    // Assert
    assertEquals(1, reports.size());
    ReportDTO report = reports.get(0);
    assertEquals("Example Location", report.getLocationName());
    assertEquals(2, report.getNumberOfSockets());
    assertEquals(2, report.getTotalChargingSessions());
  }

}