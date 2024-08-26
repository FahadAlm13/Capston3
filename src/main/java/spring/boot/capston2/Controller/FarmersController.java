package spring.boot.capston2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.capston2.Model.Farmers;
import spring.boot.capston2.Service.FarmersService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/farmers")
public class FarmersController {
    private final FarmersService farmersService;

    @GetMapping("/get")
    public ResponseEntity getAllFarmers() {
        return ResponseEntity.status(200).body(farmersService.getAllFarmers());
    }
    @PostMapping("/add")
    public ResponseEntity addFarmers(@Valid @RequestBody Farmers farmers) {
        farmersService.addFarmer(farmers);
        return ResponseEntity.status(200).body("Farmers add successful ");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateFarmers(@Valid @RequestBody Farmers farmers,@PathVariable Integer id){
        farmersService.updateFarmer(farmers,id);
        return ResponseEntity.status(200).body("Updated Farmers successful ");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFarmers(@PathVariable Integer id){
        farmersService.deleteFarmer(id);
        return ResponseEntity.status(200).body("Deleted Farmers successful ");
    }
}
