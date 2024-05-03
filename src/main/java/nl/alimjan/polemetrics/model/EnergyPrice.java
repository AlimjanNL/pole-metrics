package nl.alimjan.polemetrics.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "energy_prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnergyPrice {

  @Id
  private String id;

  private String country;

  @JsonDeserialize(using = BigDecimalDeserializer.class)
  private BigDecimal buyVolume;

  @JsonDeserialize(using = BigDecimalDeserializer.class)
  private BigDecimal sellVolume;

  @JsonDeserialize(using = BigDecimalDeserializer.class)
  private BigDecimal price;

  private String currency;
  private String unit;
  private LocalDate date;
  private int timeslot;
}
