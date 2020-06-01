package spbstu.project.variant_three.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spbstu.project.variant_three.entity.Warehouse2;
import spbstu.project.variant_three.repository.Warehouse2Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class Warehouse2Controller {
  private final Warehouse2Repository warehouse2Repository;

  @GetMapping("warehouse2")
  @PreAuthorize("isAuthenticated()")
  public List<Warehouse2> getAll() {
    return StreamSupport.stream(warehouse2Repository.findAll().spliterator(), false).collect(Collectors.toList());
  }

  @GetMapping("warehouse2/{id}")
  @PreAuthorize("isAuthenticated()")
  public Warehouse2 getOne(@PathVariable("id") Warehouse2 warehouse2) {
    return warehouse2;
  }

  @PostMapping("warehouse2")
  @PreAuthorize("isAuthenticated()")
  public Warehouse2 saveOne(@RequestBody Warehouse2 warehouse2) {
    return warehouse2Repository.save(warehouse2);
  }

  @PutMapping("warehouse2/{id}")
  @PreAuthorize("isAuthenticated()")
  public Warehouse2 updateOne(@PathVariable("id") Warehouse2 warehouse2FromDb,
                              @RequestBody Warehouse2 updatedWarehouse2) {
    BeanUtils.copyProperties(updatedWarehouse2, warehouse2FromDb, "id");

    return warehouse2Repository.save(warehouse2FromDb);
  }

  @DeleteMapping("warehouse2/{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteOne(@PathVariable("id") Warehouse2 warehouse2) {
    warehouse2Repository.delete(warehouse2);
  }
}
