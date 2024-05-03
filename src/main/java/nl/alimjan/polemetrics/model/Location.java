package nl.alimjan.polemetrics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Location {

  @Id
  private String id;

  private String name;
  private String type;
  private String address;
  private String city;

  @Column(name = "postal_code")
  @JsonProperty("postal_code")
  private String postalCode;

  private String country;

  @Embedded
  private Coordinates coordinates;

  @Column(name = "charging_when_closed")
  @JsonProperty("charging_when_closed")
  private boolean chargingWhenClosed;

  @Column(name = "last_updated")
  @JsonProperty("last_updated")
  private Instant lastUpdated;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "location")
  private List<EVSE> evses;
}

