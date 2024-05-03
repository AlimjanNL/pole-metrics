package nl.alimjan.polemetrics.client;

import java.util.List;
import nl.alimjan.polemetrics.model.EnergyPrice;
import nl.alimjan.polemetrics.model.Location;
import nl.alimjan.polemetrics.model.MeterValue;

public interface PoleClient {

  List<Location> getLocations();

  List<EnergyPrice> getEnergyPrices();

  List<MeterValue> getMeterValues();
}
