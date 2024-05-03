package nl.alimjan.polemetrics.controller;

import java.util.List;
import nl.alimjan.polemetrics.dto.LocationDTO;
import nl.alimjan.polemetrics.dto.MeterValueDTO;
import nl.alimjan.polemetrics.dto.ReportDTO;
import nl.alimjan.polemetrics.service.PoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PoleController {

  private final PoleService poleService;

  public PoleController(PoleService poleService) {
    this.poleService = poleService;
    this.poleService.fetchData();
  }

  @GetMapping("fetch-data")
  void fetchData() {
    this.poleService.fetchData();
  }

  @GetMapping("locations")
  ResponseEntity<List<LocationDTO>> getLocations(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size
  ) {
    return ResponseEntity.ok(this.poleService.getLocations(page, size));
  }

  @GetMapping("meter-values")
  ResponseEntity<List<MeterValueDTO>> getMeterValues(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    return ResponseEntity.ok(this.poleService.getMeterValues(page, size));
  }

  @GetMapping("reposts")
  ResponseEntity<List<ReportDTO>> getReports(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "1") int size
  ) {
    return ResponseEntity.ok(this.poleService.getReports(page, size));
  }
}
