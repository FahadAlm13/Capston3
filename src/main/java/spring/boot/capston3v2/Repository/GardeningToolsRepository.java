package spring.boot.capston3v2.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.capston3v2.Model.GardeningTools;

@Repository
public interface GardeningToolsRepository extends JpaRepository<GardeningTools,Integer> {
    GardeningTools findGardeningToolsById(Integer toolsId);
}
