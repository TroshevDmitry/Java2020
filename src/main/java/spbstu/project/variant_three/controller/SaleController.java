package spbstu.project.variant_three.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spbstu.project.variant_three.entity.Sale;
import spbstu.project.variant_three.repository.SaleRepository;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class SaleController {
  private final SaleRepository saleRepository;

  @GetMapping("sale")
  @PreAuthorize("isAuthenticated()")
  public List<Sale> getAll() {
    return StreamSupport.stream(saleRepository.findAll().spliterator(), false).collect(Collectors.toList());
  }

  @GetMapping("sale/{id}")
  @PreAuthorize("isAuthenticated()")
  public Sale getOne(@PathVariable("id") Sale sale) {
    return sale;
  }

  @PostMapping("sale")
  @PreAuthorize("isAuthenticated()")
  public Sale saveOne(@RequestBody Sale sale) {
    return saleRepository.save(sale);
  }

  @PutMapping("sale/{id}")
  @PreAuthorize("isAuthenticated()")
  public Sale updateOne(@PathVariable("id") Sale saleFromDb, @RequestBody Sale updatedSale) {
    BeanUtils.copyProperties(updatedSale, saleFromDb, "id");

    return saleRepository.save(saleFromDb);
  }

  @DeleteMapping("sale/{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteOne(@PathVariable("id") Sale sale) {
    saleRepository.delete(sale);
  }
}
