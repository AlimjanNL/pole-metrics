package nl.alimjan.polemetrics.client.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "meter_values")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeterValue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Instant date;
  private Instant dateAdded;
  private String physicalReference;
  private String transactionId;
  private double meterValue;
}
