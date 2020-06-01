package spbstu.project.variant_three.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spbstu.project.variant_three.entity.Good;
import spbstu.project.variant_three.repository.GoodRepository;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class GoodController {
  private final GoodRepository goodRepository;

  @GetMapping("good")
  @PreAuthorize("isAuthenticated()")
  public List<Good> getAll() {
    return StreamSupport.stream(goodRepository.findAll().spliterator(), false).collect(Collectors.toList());
  }

  @GetMapping("good/{id}")
  @PreAuthorize("isAuthenticated()")
  public Good getOne(@PathVariable("id") Good good) {
    return good;
  }

  @PostMapping("good")
  @PreAuthorize("isAuthenticated()")
  public Good saveOne(@RequestBody Good good) {
    return goodRepository.save(good);
  }

  @PutMapping("good/{id}")
  @PreAuthorize("isAuthenticated()")
  public Good updateOne(@PathVariable("id") Good goodFromDb, @RequestBody Good updatedGood) {
    BeanUtils.copyProperties(updatedGood, goodFromDb, "id");

    return goodRepository.save(goodFromDb);
  }

  @DeleteMapping("good/{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteOne(@PathVariable("id") Good good) {
    goodRepository.delete(good);
  }
}
