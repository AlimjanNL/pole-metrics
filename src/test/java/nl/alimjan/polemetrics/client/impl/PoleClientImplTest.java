package nl.alimjan.polemetrics.client.impl;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import nl.alimjan.polemetrics.model.EnergyPrice;
import nl.alimjan.polemetrics.model.Location;
import nl.alimjan.polemetrics.model.MeterValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class PoleClientImplTest {

  @Autowired
  private PoleClientImpl poleClient;

  @Test
  public void testGetLocations() {
    List<Location> locationList = poleClient.getLocations();

    assertThat(locationList)
        .isNotNull()
        .isNotEmpty()
        .hasSize(2);
  }

  @Test
  public void testGetEnergyPrices() {
    List<EnergyPrice> energyPriceList = poleClient.getEnergyPrices();

    assertThat(energyPriceList)
        .isNotNull()
        .isNotEmpty()
        .hasSize(3);
  }


  @Test
  public void testGetMeterValues() {
    List<MeterValue> meterValueList = poleClient.getMeterValues();

    assertThat(meterValueList)
        .isNotNull()
        .isNotEmpty()
        .hasSize(3);
  }
}
