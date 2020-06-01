package spbstu.project.variant_three.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spbstu.project.variant_three.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String name);
}
