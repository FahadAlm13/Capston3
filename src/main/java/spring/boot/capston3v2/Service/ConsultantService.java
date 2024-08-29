package spring.boot.capston3v2.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.capston3v2.Api.ApiException;
import spring.boot.capston3v2.Model.Consultant;
import spring.boot.capston3v2.Model.Consultation;
import spring.boot.capston3v2.Repository.ConsultantRepository;
import spring.boot.capston3v2.Repository.ConsultationRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultantService {
    private final ConsultantRepository consultantRepository;
    private final ConsultationRepository consultationRepository;

    public List<Consultant> getAllConsultants() {

        return consultantRepository.findAll();
    }
    public void addNewConsultant(Consultant consultant) {
        consultant.setRegistrationDate(LocalDate.now());
        consultant.setConsultantConfirmed(false);
        consultant.setUpdatedAt(LocalDate.now());
        consultantRepository.save(consultant);
    }

    public void updateConsultant(Integer id,Consultant consultant) {
        Consultant consultant1 = consultantRepository.findConsultantById(id);
        if (consultant1 == null) {
            throw new ApiException("Consultant not found");
        }
      consultant1.setFullName(consultant.getFullName());
      consultant1.setEmail(consultant.getEmail());
      consultant1.setPassword(consultant.getPassword());
      consultant1.setSpecialization(consultant.getSpecialization());
      consultant1.setYearsOfExperience(consultant.getYearsOfExperience());
      consultant1.setUpdatedAt(LocalDate.now());
      consultantRepository.save(consultant1);
    }


    public void deleteConsultant(Integer id) {
        Consultant consultant= consultantRepository.findConsultantById(id);
        if (consultant == null) {
            throw new ApiException("Consultant not found");
        }
        consultantRepository.delete(consultant);
    }

    public void confirmConsultationSchedule(Integer consultationId,Integer consultantId,String status) {
        Consultation consultation = consultationRepository.findConsultationById(consultationId);
        Consultant consultant=consultantRepository.findConsultantById(consultantId);
        if(consultation==null) {
            throw new ApiException("Consultation not found");
        }
        if(consultant==null) {
            throw new ApiException("consultant id not found");
        }
         if (!consultation.getConsultant().equals(consultant)) {
            throw new ApiException("Consultant is not the same");
        }

        if (!consultation.getStatus().equalsIgnoreCase("PENDING")) {
            throw new ApiException("Consultation is not in Pending status");
        }
        if(status.equalsIgnoreCase("agree")){
            consultation.setStatus("Scheduled");
            consultation.setUpdatedAt(LocalDate.now());
            // available
        }
        consultationRepository.save(consultation);


    }

    public void consultantConfirmed(Integer id,Integer bookingId ){
        Consultant consultant=consultantRepository.findConsultantById(id);
        Consultation consultation=consultationRepository.findConsultationById(bookingId);
        if(consultant==null || consultation==null) {
            throw new ApiException("consultant or consultation not found");
        }
         if (!consultation.getConsultant().equals(consultant)) {
            throw new ApiException("Consultant is not the same");
        }
        if(consultation.getStatus().equalsIgnoreCase("Completed")){
            throw new ApiException("It is Completed.");
        }
        if(consultation.getStatus().equalsIgnoreCase("PENDING")){
            throw new ApiException("The consultation was not approved.");
        }
        if(consultation.getStatus().equalsIgnoreCase("Canceled")){
            throw new ApiException("The consultation is already Canceled.");
        }
        if(consultant.getConsultantConfirmed().equals(true)){
            throw new ApiException("The session has been confirmed.");
        }
        consultant.setUpdatedAt(LocalDate.now());
        consultant.setConsultantConfirmed(true);
        consultantRepository.save(consultant);

    }

    public void canceledStatusOfConsultation(Integer consultantId,Integer consultationId){
        Consultant consultant=consultantRepository.findConsultantById(consultantId);
        Consultation consultation=consultationRepository.findConsultationById(consultationId);
        if(consultant==null||consultation==null){
            throw new ApiException("consultant or consultation not found");
        }
         if (!consultation.getConsultant().equals(consultant)) {
            throw new ApiException("Consultant is not the same");
        }
        if(consultation.getStatus().equalsIgnoreCase("Canceled")){
            throw new ApiException("it is already canceled");
        }
        // Set the status to "Canceled"
        consultation.setStatus("Canceled");
        consultationRepository.save(consultation);
    }

}
