package nl.alimjan.polemetrics.service.impl;

import java.util.List;
import nl.alimjan.polemetrics.client.PoleClient;
import nl.alimjan.polemetrics.client.model.Connector;
import nl.alimjan.polemetrics.client.model.EVSE;
import nl.alimjan.polemetrics.client.model.EnergyPrice;
import nl.alimjan.polemetrics.client.model.Location;
import nl.alimjan.polemetrics.client.model.MeterValue;
import nl.alimjan.polemetrics.repository.EnergyPriceRepository;
import nl.alimjan.polemetrics.repository.LocationRepository;
import nl.alimjan.polemetrics.repository.MeterValueRepository;
import nl.alimjan.polemetrics.service.PoleService;
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

  public void updateDB() {
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
}
