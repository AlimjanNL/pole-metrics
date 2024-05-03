package nl.alimjan.polemetrics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "evses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EVSE {

  @Id
  private String uid;

  @Column(name = "evse_id")
  @JsonProperty("evse_id")
  private String evseId;

  private String status;

  @Column(name = "last_updated")
  @JsonProperty("last_updated")
  private Instant lastUpdated;

  @Column(name = "physical_reference")
  @JsonProperty("physical_reference")
  private String physicalReference;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "evse")
  private List<Connector> connectors;
}
