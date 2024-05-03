package nl.alimjan.polemetrics.client.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import nl.alimjan.polemetrics.client.PoleClient;
import nl.alimjan.polemetrics.model.EnergyPrice;
import nl.alimjan.polemetrics.model.Location;
import nl.alimjan.polemetrics.model.MeterValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PoleClientImpl implements PoleClient {

  @Value("${data.path.locations:/data/locations.json}")
  private String locationsPath;

  @Value("${data.path.energy-prices:/data/energyPrices.json}")
  private String energyPricesPath;

  @Value("${data.path.meter-values:/data/meterValues.json}")
  private String meterValuesPath;

  private final ObjectMapper objectMapper;

  public PoleClientImpl(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public List<Location> getLocations() {
    return fetchData(locationsPath, new TypeReference<>() {
    });
  }

  @Override
  public List<EnergyPrice> getEnergyPrices() {
    return fetchData(energyPricesPath, new TypeReference<>() {
    });
  }

  @Override
  public List<MeterValue> getMeterValues() {
    return fetchData(meterValuesPath, new TypeReference<>() {
    });
  }

  protected  <T> List<T> fetchData(String filePath, TypeReference<List<T>> typeReference) {
    try (InputStream inputStream = TypeReference.class.getResourceAsStream(filePath)) {
      return objectMapper.readValue(inputStream, typeReference);
    } catch (IOException ex) {
      log.error("Failed to load JSON from file: {}", filePath, ex);
      return List.of();
    }
  }
}
