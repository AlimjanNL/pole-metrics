package nl.alimjan.polemetrics.repository;

import nl.alimjan.polemetrics.model.EnergyPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyPriceRepository extends JpaRepository<EnergyPrice, Long> {

}
