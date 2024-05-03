package nl.alimjan.polemetrics.repository;

import nl.alimjan.polemetrics.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

}
