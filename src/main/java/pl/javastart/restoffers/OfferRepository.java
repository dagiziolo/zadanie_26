package pl.javastart.restoffers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
//    @Query("SELECT o, c.name FROM Offer o " +
//            "join Category c on c.id = o.category_id " +
//            " WHERE 1=1 " +
//            " AND (:title IS NULL OR m.title LIKE CONCAT('%', :title, '%'))"
//    )
//    List<Offer> findUsingTitle(@Param("title") String title);
//List<Offer> findAllByTitle(String title);

}
