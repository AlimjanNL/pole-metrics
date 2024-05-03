package nl.alimjan.polemetrics.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDTO {

  private String locationName;
  private int numberOfSockets;
  private double totalKWhCharged;
  private int totalChargingSessions;
  private Map<String, Double> kWhChargedPerSocket;
  private double kWhChargedPerSession;
  private Map<String, Map<String, Double>> dailyKWhPerSocket;
}
