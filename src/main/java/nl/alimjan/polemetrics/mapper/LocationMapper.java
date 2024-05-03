package nl.alimjan.polemetrics.mapper;

import java.util.List;
import nl.alimjan.polemetrics.dto.EVSEDTO;
import nl.alimjan.polemetrics.dto.LocationDTO;
import nl.alimjan.polemetrics.model.Location;

public class LocationMapper {

  public static LocationDTO toDTO(Location location) {
    LocationDTO dto = new LocationDTO();
    dto.setId(location.getId());
    dto.setName(location.getName());
    dto.setType(location.getType());
    dto.setAddress(location.getAddress());
    dto.setCity(location.getCity());
    dto.setCountry(location.getCountry());
    dto.setCoordinates(location.getCoordinates());
    dto.setChargingWhenClosed(location.isChargingWhenClosed());
    dto.setLastUpdated(location.getLastUpdated());

    if (location.getEvses() != null) {
      List<EVSEDTO> evsedtoList = location.getEvses()
          .stream()
          .map(EVSEMapper::toDTO)
          .toList();

      dto.setEvses(evsedtoList);
    }

    return dto;
  }
}
