package nl.alimjan.polemetrics.dto;

import java.time.Instant;
import java.util.List;
import lombok.Data;
import nl.alimjan.polemetrics.model.Coordinates;

@Data
public class LocationDTO {

  private String id;
  private String name;
  private String type;
  private String address;
  private String city;
  private String country;
  private Coordinates coordinates;
  private boolean chargingWhenClosed;
  private Instant lastUpdated;
  private List<EVSEDTO> evses;
}
