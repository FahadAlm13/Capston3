package spring.boot.capston3v2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.capston3v2.Api.ApiResponse;
import spring.boot.capston3v2.Model.Nurseries;
import spring.boot.capston3v2.Model.Plants;
import spring.boot.capston3v2.Service.NurseriesService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/nurseries")
@RequiredArgsConstructor
public class NurseriesController {
    private final NurseriesService nurseriesService;

    @GetMapping("/get")
    public ResponseEntity getAllNurseries() {
        return ResponseEntity.status(200).body(nurseriesService.getAllNurseries());
    }

    @PostMapping("/add/{farmer_id}")
    public ResponseEntity addNurseries(@Valid @RequestBody Nurseries nurseries, @PathVariable Integer farmer_id) {
        nurseriesService.addNurseries(nurseries, farmer_id);
        return ResponseEntity.status(200).body(new ApiResponse("Nurseries add successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNurseries(@Valid @RequestBody Nurseries nurseries, @PathVariable Integer id) {
        nurseriesService.updateNurseries(nurseries, id);
        return ResponseEntity.status(200).body("Nurseries update successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNurseries(@PathVariable Integer id) {
        nurseriesService.deleteNurseries(id);
        return ResponseEntity.status(200).body("Nurseries delete successfully");
    }

    @GetMapping("/getListOfPlants/{nurseries_id}")
    public ResponseEntity getListOfPlantsToNurseries(@PathVariable Integer nurseries_id) {
        return ResponseEntity.status(200).body(nurseriesService.getListOfPlantsToNurseries(nurseries_id));
    }

    @GetMapping("/availableStock/{plant_id}")
    public ResponseEntity getNurseriesWithAvailableStock(@PathVariable Integer plant_id) {
        return ResponseEntity.status(200).body(nurseriesService.getNurseriesWithAvailablePlantStock(plant_id));
    }

    @GetMapping("/byLocation/{location}")
    public ResponseEntity getNurseriesByLocation(@PathVariable String location) {
        return ResponseEntity.status(200).body(nurseriesService.getNurseriesByLocation(location));
    }

//    @GetMapping("/{nurseryId}/plants")
//    public ResponseEntity getAllPlantsByNursery(@PathVariable Integer nurseryId) {
//        Set<Plants> plants = nurseriesService.getAllPlantsByNursery(nurseryId);
//        return ResponseEntity.status(200).body(plants);
//    }


}
