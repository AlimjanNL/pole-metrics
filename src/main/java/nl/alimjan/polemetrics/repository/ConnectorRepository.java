package nl.alimjan.polemetrics.repository;

import nl.alimjan.polemetrics.client.model.Connector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectorRepository extends JpaRepository<Connector, Long> {

}
