package spring.boot.capston3v2.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.boot.capston3v2.Model.Farmers;

import java.util.List;

@Repository
public interface FarmersRepository extends JpaRepository<Farmers, Integer> {
    Farmers findFarmersById(Integer id);

    @Query("SELECT f FROM Farmers f WHERE f.address LIKE %:location%")
    List<Farmers> findFarmersByLocation(@Param("location") String location);
}
