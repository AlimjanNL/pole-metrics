package nl.alimjan.polemetrics.repository;

import nl.alimjan.polemetrics.model.MeterValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterValueRepository extends JpaRepository<MeterValue, Long> {

}
