package nl.alimjan.polemetrics.service;

import java.util.List;
import nl.alimjan.polemetrics.dto.LocationDTO;
import nl.alimjan.polemetrics.dto.MeterValueDTO;
import nl.alimjan.polemetrics.dto.ReportDTO;
import nl.alimjan.polemetrics.model.Location;

public interface PoleService {

  void fetchData();

  List<LocationDTO> getLocations(int page, int size);

  List<MeterValueDTO> getMeterValues(int page, int size);

  List<ReportDTO> getReports(int page, int size);
}
