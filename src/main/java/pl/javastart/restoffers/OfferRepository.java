package pl.javastart.restoffers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("SELECT o FROM Offer o " +
            " WHERE 1=1 " +
            " AND (:title IS NULL OR lower(o.title) LIKE CONCAT('%', :title, '%'))"
    )
    List<Offer> findUsingTitle(@Param("title") String title);
//List<Offer> findAllByTitle(String title);
//
//    @Query(value = "select o.id, o.title, o.description, o.imgUrl, o.price, o.category.name FROM Offer o ")
//    List<Object> findAllWithCategory();
}
