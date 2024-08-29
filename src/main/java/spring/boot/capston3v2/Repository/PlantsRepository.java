package spring.boot.capston3v2.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.boot.capston3v2.Model.Plants;

import java.util.List;
import java.util.Set;

@Repository
public interface PlantsRepository extends JpaRepository<Plants, Integer> {
    Plants findPlantsById(Integer id);

    Set<Plants> findPlantsByCategory(String category);

}
