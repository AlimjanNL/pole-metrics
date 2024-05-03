package nl.alimjan.polemetrics.dto;

import java.time.Instant;
import java.util.List;
import lombok.Data;

@Data
public class EVSEDTO {

  private String uid;
  private String evseId;
  private String status;
  private Instant lastUpdated;
  private String physicalReference;
  private String locationId;
  private List<ConnectorDTO> connectors;
}
