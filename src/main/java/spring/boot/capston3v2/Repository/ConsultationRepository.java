package spring.boot.capston3v2.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.boot.capston3v2.Model.Consultation;

import java.time.LocalDate;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    Consultation findConsultationById(Integer id);

    @Query("SELECT CASE WHEN DATEDIFF(c.consultationDate, :currentDate) > 0 THEN true ELSE false END " +
            "FROM Consultation c " +
            "WHERE c.id = :id AND c.status <> 'Canceled'")
    boolean canCancelBeforeConsultationDate( Integer id,  LocalDate currentDate);
}
