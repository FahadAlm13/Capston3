package spring.boot.capston3v2.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.boot.capston3v2.Model.Orders;
import spring.boot.capston3v2.Model.Reviews;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
    Reviews findReviewsById(Integer revId);

    List<Reviews> findAllByOrders(Orders orders);

    @Query("SELECT r FROM Reviews r WHERE r.rating = :rating")
    List<Reviews> findAllByRating(@Param("rating") Integer rating);

}
