package nl.alimjan.polemetrics.mapper;

import nl.alimjan.polemetrics.dto.MeterValueDTO;
import nl.alimjan.polemetrics.model.MeterValue;

public class MeterValueMapper {

  public static MeterValueDTO toDTO(MeterValue meterValue) {
    MeterValueDTO dto = new MeterValueDTO();

    dto.setId(meterValue.getId());
    dto.setDate(meterValue.getDate());
    dto.setDateAdded(meterValue.getDateAdded());
    dto.setPhysicalReference(meterValue.getPhysicalReference());
    dto.setTransactionId(meterValue.getTransactionId());
    dto.setMeterValue(meterValue.getMeterValue());

    return dto;
  }
}
