package spring.boot.capston3v2.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.capston3v2.Api.ApiResponse;
import spring.boot.capston3v2.Model.Plans;
import spring.boot.capston3v2.Service.PlansService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class PlansController {
    private final PlansService plansService;

    @GetMapping("/get")
    public ResponseEntity getPlans(){
        return ResponseEntity.status(200).body(plansService.getAllPlans());
    }

    @PostMapping("/add/{consultant_id}")
    public ResponseEntity addPlans(@Valid @RequestBody Plans plans, @PathVariable Integer consultant_id){
        plansService.addPlans(plans,consultant_id);
        return ResponseEntity.status(200).body(new ApiResponse("Plan added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePlans(@PathVariable Integer id,@Valid@RequestBody Plans plans){

        plansService.updatePlans(id,plans);
        return ResponseEntity.status(200).body(new ApiResponse("Plan Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePlans(@PathVariable Integer id){
        plansService.deletePlans(id);
        return ResponseEntity.status(200).body(new ApiResponse("Plan deleted"));
    }
    @GetMapping("/byConsultantSpecialization/{specialization}")
    public ResponseEntity getPlansByConsultantSpecialization(@PathVariable String specialization) {
        List<Plans> plans = plansService.getPlansByConsultantSpecialization(specialization);
        return ResponseEntity.status(200).body(plans);
    }

}
