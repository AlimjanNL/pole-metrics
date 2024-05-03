package nl.alimjan.polemetrics.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import nl.alimjan.polemetrics.client.PoleClient;
import nl.alimjan.polemetrics.dto.LocationDTO;
import nl.alimjan.polemetrics.dto.MeterValueDTO;
import nl.alimjan.polemetrics.dto.ReportDTO;
import nl.alimjan.polemetrics.mapper.LocationMapper;
import nl.alimjan.polemetrics.mapper.MeterValueMapper;
import nl.alimjan.polemetrics.model.Connector;
import nl.alimjan.polemetrics.model.EVSE;
import nl.alimjan.polemetrics.model.EnergyPrice;
import nl.alimjan.polemetrics.model.Location;
import nl.alimjan.polemetrics.model.MeterValue;
import nl.alimjan.polemetrics.repository.EnergyPriceRepository;
import nl.alimjan.polemetrics.repository.LocationRepository;
import nl.alimjan.polemetrics.repository.MeterValueRepository;
import nl.alimjan.polemetrics.service.PoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PoleServiceImpl implements PoleService {

  private final PoleClient poleClient;
  private final LocationRepository locationRepository;
  private final MeterValueRepository meterValueRepository;
  private final EnergyPriceRepository energyPriceRepository;

  public PoleServiceImpl(
      PoleClient poleClient,
      LocationRepository locationRepository,
      MeterValueRepository meterValueRepository,
      EnergyPriceRepository energyPriceRepository
  ) {
    this.poleClient = poleClient;
    this.locationRepository = locationRepository;
    this.meterValueRepository = meterValueRepository;
    this.energyPriceRepository = energyPriceRepository;
  }

  public void fetchData() {
    // Fetch data
    List<Location> locations = poleClient.getLocations();
    List<MeterValue> meterValues = poleClient.getMeterValues();
    List<EnergyPrice> energyPrices = poleClient.getEnergyPrices();

    // set relation
    for (Location location : locations) {
      if (location.getEvses() != null) {
        for (EVSE evse : location.getEvses()) {
          if (evse.getConnectors() != null) {
            for (Connector connector : evse.getConnectors()) {
              connector.setEvse(evse);
            }
          }
          evse.setLocation(location);
        }
      }
    }

    locationRepository.saveAll(locations);
    meterValueRepository.saveAll(meterValues);
    energyPriceRepository.saveAll(energyPrices);
  }

  public List<LocationDTO> getLocations(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Location> locationsPage = locationRepository.findAll(pageable);

    return locationsPage.getContent()
        .stream()
        .map(LocationMapper::toDTO)
        .toList();
  }

  public List<MeterValueDTO> getMeterValues(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<MeterValue> meterValuesPage = meterValueRepository.findAll(pageable);

    return meterValuesPage.getContent()
        .stream()
        .map(MeterValueMapper::toDTO)
        .toList();
  }

  public List<ReportDTO> getReports(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Location> locationsPage = locationRepository.findAll(pageable);

    return locationsPage.stream().map(this::createReportForLocation).collect(Collectors.toList());
  }

  private ReportDTO createReportForLocation(Location location) {
    int totalSessions = 0;
    double totalKWh = 0;
    Map<String, Double> kWhPerSocket = new HashMap<>();
    Map<String, Map<String, Double>> dailyKWhPerSocket = new HashMap<>();

    for (EVSE evse : location.getEvses()) {
      for (Connector connector : evse.getConnectors()) {
        String socketId = connector.getId();
        double kWh = fetchKWhForConnector(connector);
        totalKWh += kWh;
        totalSessions++;

        kWhPerSocket.merge(socketId, kWh, Double::sum);

        Map<String, Double> dailyKWh = fetchDailyKWhForConnector(connector);
        dailyKWhPerSocket.put(socketId, dailyKWh);
      }
    }

    return ReportDTO.builder()
        .locationName(location.getName())
        .numberOfSockets(location.getEvses().size())
        .totalKWhCharged(totalKWh)
        .totalChargingSessions(totalSessions)
        .kWhChargedPerSocket(kWhPerSocket)
        .kWhChargedPerSession(totalKWh / totalSessions)
        .dailyKWhPerSocket(dailyKWhPerSocket)
        .build();
  }

  private double fetchKWhForConnector(Connector connector) {
    // Simulate duration time
    Random random = new Random();
    int durationHours = 1 + random.nextInt(10);
    int powerType = this.extractNumberFromPowerType(connector.getPowerType());

    double calculatedPower = calculatePower(connector.getVoltage(), connector.getAmperage(), powerType)
        * durationHours;

    BigDecimal formattedKWh = BigDecimal.valueOf(calculatedPower).setScale(2, RoundingMode.HALF_UP);

    return formattedKWh.doubleValue();
  }

  private Map<String, Double> fetchDailyKWhForConnector(Connector connector) {
    Map<String, Double> dailyKWh = new HashMap<>();
    Random random = new Random();

    // // Simulate week
    LocalDate today = LocalDate.now();
    int daysToGenerate = 7;

    for (int i = 0; i < daysToGenerate; i++) {
      LocalDate date = today.minusDays(i);
      // Simulate daily kWh usage
      double simulatedDailyKWh = 10 + (40 * random.nextDouble());
      BigDecimal formattedKWh = BigDecimal.valueOf(simulatedDailyKWh).setScale(2, RoundingMode.HALF_UP);

      dailyKWh.put(date.toString(), formattedKWh.doubleValue());
    }

    return dailyKWh;
  }

  private double calculatePower(int voltage, int amperage, int powerType) {
    return voltage * amperage * Math.sqrt(powerType) / 1000.0;
  }

  private int extractNumberFromPowerType(String powerType) {
    Pattern pattern = Pattern.compile("\\d+");
    Matcher matcher = pattern.matcher(powerType);

    if (matcher.find()) {
      return Integer.parseInt(matcher.group());
    }
    return 0;
  }
}
