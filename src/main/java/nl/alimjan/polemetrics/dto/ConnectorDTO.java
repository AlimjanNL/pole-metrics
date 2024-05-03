package nl.alimjan.polemetrics.dto;

import java.time.Instant;
import lombok.Data;

@Data
public class ConnectorDTO {

  private String id;
  private String standard;
  private String format;
  private String powerType;
  private int voltage;
  private int amperage;
  private Instant lastUpdated;
  private String tariffId;
  private String evseId;
}
