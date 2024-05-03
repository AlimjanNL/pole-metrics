package nl.alimjan.polemetrics.dto;

import java.time.Instant;
import lombok.Data;

@Data
public class MeterValueDTO {

  private Long id;
  private Instant date;
  private Instant dateAdded;
  private String physicalReference;
  private String transactionId;
  private double meterValue;
}
