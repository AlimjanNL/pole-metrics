package nl.alimjan.polemetrics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "connectors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Connector {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long unique_id;

  private String id;
  private String standard;
  private String format;

  @Column(name = "power_type")
  @JsonProperty("power_type")
  private String powerType;

  private int voltage;
  private int amperage;

  @Column(name = "last_updated")
  @JsonProperty("last_updated")
  private Instant lastUpdated;

  @Column(name = "tariff_id")
  @JsonProperty("tariff_id")
  private String tariffId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "evse_id")
  private EVSE evse;
}
