package spring.boot.capston3v2.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.capston3v2.Model.Consultant;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Integer> {

    Consultant findConsultantById(Integer id);
}

