package spring.boot.capston3v2.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.capston3v2.Api.ApiException;
import spring.boot.capston3v2.Model.Consultant;
import spring.boot.capston3v2.Model.Plans;
import spring.boot.capston3v2.Repository.ConsultantRepository;
import spring.boot.capston3v2.Repository.PlansRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlansService {

    private final PlansRepository plansRepository;
    private final ConsultantRepository consultantRepository;

    public List<Plans> getAllPlans() {
        return plansRepository.findAll();
    }

    public void addPlans(Plans plans, Integer consultant_id) {
        Consultant consultant = consultantRepository.findConsultantById(consultant_id);
        if (consultant == null) {
            throw new ApiException("Consultant not found");
        }
        plans.setConsultant(consultant);
        plansRepository.save(plans);
    }

    public void updatePlans(Integer id, Plans plans) {
        Plans plans1 = plansRepository.findPlansById(id);
        if (plans1 == null) {
            throw new ApiException("not found");
        }
        plans1.setSchedule(plans.getSchedule());
        plans1.setPlanFee(plans.getPlanFee());
        plans1.setRecommendation(plans.getRecommendation());
        plans1.setPlants(plans.getPlants());

        plansRepository.save(plans1);

    }

    public void deletePlans(Integer id) {
        Plans plans = plansRepository.findPlansById(id);
        if (plans == null) {
            throw new ApiException("user not found");
        }
        plansRepository.delete(plans);
    }

    public List<Plans> getPlansByConsultantSpecialization(String specialization) {
        return plansRepository.findPlansByConsultantSpecialization(specialization);
    }


}
