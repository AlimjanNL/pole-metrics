package nl.alimjan.polemetrics.mapper;

import nl.alimjan.polemetrics.dto.ConnectorDTO;
import nl.alimjan.polemetrics.model.Connector;

public class ConnectorMapper {

  public static ConnectorDTO toDTO(Connector connector) {
    ConnectorDTO dto = new ConnectorDTO();
    dto.setId(connector.getId());
    dto.setStandard(connector.getStandard());
    dto.setFormat(connector.getFormat());
    dto.setPowerType(connector.getPowerType());
    dto.setVoltage(connector.getVoltage());
    dto.setAmperage(connector.getAmperage());
    dto.setLastUpdated(connector.getLastUpdated());
    dto.setTariffId(connector.getTariffId());
    dto.setEvseId(connector.getEvse().getEvseId());

    return dto;
  }
}
