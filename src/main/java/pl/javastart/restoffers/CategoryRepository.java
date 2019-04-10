package pl.javastart.restoffers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select c.name FROM Category c ")
    List<String> findAllNames();

    Category findByName(String name);
}
