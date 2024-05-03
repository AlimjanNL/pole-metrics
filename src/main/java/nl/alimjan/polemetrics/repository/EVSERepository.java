package nl.alimjan.polemetrics.repository;

import nl.alimjan.polemetrics.model.EVSE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EVSERepository extends JpaRepository<EVSE, Long> {

}
