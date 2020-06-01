package spbstu.project.variant_three.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spbstu.project.variant_three.entity.Warehouse1;
import spbstu.project.variant_three.repository.Warehouse1Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class Warehouse1Controller {
  private final Warehouse1Repository warehouse1Repository;

  @GetMapping("warehouse1")
  @PreAuthorize("isAuthenticated()")
  public List<Warehouse1> getAll() {
    return StreamSupport.stream(warehouse1Repository.findAll().spliterator(), false).collect(Collectors.toList());
  }

  @GetMapping("warehouse1/{id}")
  @PreAuthorize("isAuthenticated()")
  public Warehouse1 getOne(@PathVariable("id") Warehouse1 warehouse1) {
    return warehouse1;
  }

  @PostMapping("warehouse1")
  @PreAuthorize("isAuthenticated()")
  public Warehouse1 saveOne(@RequestBody Warehouse1 warehouse1) {
    return warehouse1Repository.save(warehouse1);
  }

  @PutMapping("warehouse1/{id}")
  @PreAuthorize("isAuthenticated()")
  public Warehouse1 updateOne(@PathVariable("id") Warehouse1 warehouse1FromDb,
                              @RequestBody Warehouse1 updatedWarehouse1) {
    BeanUtils.copyProperties(updatedWarehouse1, warehouse1FromDb, "id");

    return warehouse1Repository.save(warehouse1FromDb);
  }

  @DeleteMapping("warehouse1/{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteOne(@PathVariable("id") Warehouse1 warehouse1) {
    warehouse1Repository.delete(warehouse1);
  }
}
