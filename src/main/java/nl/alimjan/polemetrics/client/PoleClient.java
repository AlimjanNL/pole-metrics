package nl.alimjan.polemetrics.client;

import java.util.List;
import nl.alimjan.polemetrics.client.model.EnergyPrice;
import nl.alimjan.polemetrics.client.model.Location;
import nl.alimjan.polemetrics.client.model.MeterValue;

public interface PoleClient {

  List<Location> getLocations();

  List<EnergyPrice> getEnergyPrices();

  List<MeterValue> getMeterValues();
}
