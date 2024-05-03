package nl.alimjan.polemetrics.service.impl;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import nl.alimjan.polemetrics.model.EnergyPrice;
import nl.alimjan.polemetrics.model.Location;
import nl.alimjan.polemetrics.model.MeterValue;
import nl.alimjan.polemetrics.repository.EnergyPriceRepository;
import nl.alimjan.polemetrics.repository.LocationRepository;
import nl.alimjan.polemetrics.repository.MeterValueRepository;
import nl.alimjan.polemetrics.service.PoleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class PoleServiceImplIntegrationTest {

  @Autowired
  private PoleService poleService;

  @Autowired
  private LocationRepository locationRepository;

  @Autowired
  private MeterValueRepository meterValueRepository;

  @Autowired
  private EnergyPriceRepository energyPriceRepository;

  @Container
  protected static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:latest")
          .withDatabaseName("test-pole-metrics")
          .withUsername("postgres")
          .withPassword("password");

  @BeforeAll
  static void beforeAll() {
    postgreSQLContainer.start();
    System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
    System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
    System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
  }

  @Test
  void canStartPostgresDB() {
    assertThat(postgreSQLContainer.isRunning()).isTrue();
    assertThat(postgreSQLContainer.isCreated()).isTrue();
  }

  @Test
  void fetchData() {
    // Act
    poleService.fetchData();

    List<Location> savedLocations = locationRepository.findAll();
    List<MeterValue> savedMeterValues = meterValueRepository.findAll();
    List<EnergyPrice> savedEnergyPrices = energyPriceRepository.findAll();

    // Assert
    assertNotNull(savedLocations);
    assertNotNull(savedMeterValues);
    assertNotNull(savedEnergyPrices);

    assertThat(savedLocations).hasSizeGreaterThan(4);
    assertThat(savedMeterValues).hasSizeGreaterThan(5000);
    assertThat(savedEnergyPrices).hasSizeGreaterThan(700);
  }
}