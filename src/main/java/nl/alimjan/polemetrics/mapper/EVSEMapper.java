package nl.alimjan.polemetrics.mapper;

import java.util.List;
import nl.alimjan.polemetrics.dto.ConnectorDTO;
import nl.alimjan.polemetrics.dto.EVSEDTO;
import nl.alimjan.polemetrics.model.EVSE;

public class EVSEMapper {

  public static EVSEDTO toDTO(EVSE evse) {
    EVSEDTO dto = new EVSEDTO();

    dto.setUid(evse.getUid());
    dto.setEvseId(evse.getEvseId());
    dto.setStatus(evse.getStatus());
    dto.setLastUpdated(evse.getLastUpdated());
    dto.setPhysicalReference(evse.getPhysicalReference());
    dto.setLocationId(evse.getLocation().getId());

    if (evse.getConnectors() != null) {
      List<ConnectorDTO> connectorDTOList = evse.getConnectors()
          .stream()
          .map(ConnectorMapper::toDTO)
          .toList();

      dto.setConnectors(connectorDTOList);
    }

    return dto;
  }
}
