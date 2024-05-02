package nl.alimjan.polemetrics.controller;

import java.util.List;
import nl.alimjan.polemetrics.client.model.Location;
import nl.alimjan.polemetrics.service.PoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PoleController {

  private final PoleService poleService;

  public PoleController(PoleService poleService) {
    this.poleService = poleService;
  }

  @GetMapping("update")
  List<Location> updateDB() {
    this.poleService.updateDB();
    return null;
  }
}
