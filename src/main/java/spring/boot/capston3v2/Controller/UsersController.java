package spring.boot.capston3v2.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.capston3v2.Api.ApiResponse;
import spring.boot.capston3v2.Model.Consultation;
import spring.boot.capston3v2.Model.Nurseries;
import spring.boot.capston3v2.Model.Plans;
import spring.boot.capston3v2.Model.Users;
import spring.boot.capston3v2.Service.NurseriesService;
import spring.boot.capston3v2.Service.UsersService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(usersService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUsers(@Valid @RequestBody Users users){

        usersService.addUsers(users);
        return ResponseEntity.status(200).body(new ApiResponse("User added Successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUsers(@PathVariable Integer id,@Valid@RequestBody Users users){

        usersService.updateUsers(id,users);
        return ResponseEntity.status(200).body(new ApiResponse("User Updated Successfully!"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUsers(@PathVariable Integer id){
        usersService.deleteUsers(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted Successfully!"));
    }
    //many to many :assign Plan To User
    @PutMapping ("/assign/planId/{planId}/userId/{userId}")
    public ResponseEntity assignPlanToUser(@PathVariable Integer planId,@PathVariable Integer userId) {
        usersService.assignPlanToUser(planId, userId);
        return ResponseEntity.status(200).body(new ApiResponse("Plan assigned Successfully to user"));
    }
    @GetMapping("/recommendation")
    public ResponseEntity getPlanRecommendation(@RequestParam String plantName) {
        Plans plan = usersService.getPlanRecommendationByPlantName(plantName);
        return ResponseEntity.status(200).body(plan);
    }
    @PutMapping("/confirmed/{userId}/{bookingId}")
    public ResponseEntity consultantConfirmed(@PathVariable Integer userId,@PathVariable Integer bookingId){
        usersService.userConfirmed(userId,bookingId);
        return ResponseEntity.status(200).body(new ApiResponse("Session end confirmed"));
    }
    @GetMapping("/getAllNurseries")
    public ResponseEntity getAllNurseries(){
        return ResponseEntity.status(200).body(usersService.getAllNurseries());
    }
    @GetMapping("/byLocation/{location}")
    public ResponseEntity getNurseriesByLocation(@PathVariable String location) {
        return ResponseEntity.status(200).body(usersService.getNurseriesByLocation(location));
    }
//    private final NurseriesService nurseriesService;
//
//    @GetMapping("/findNurseriesByLocation/{location}")
//    public ResponseEntity  findNurseriesByLocation(@PathVariable String location) {
//        List<Nurseries> nurseries = nurseriesService.getNurseriesByLocation(location);
//        return ResponseEntity.status(200).body(nurseries);
//    }
    // موجودة من قبل
//    @GetMapping("/{userId}/consultations")
//    public ResponseEntity getConsultationsForUser(@PathVariable Integer userId) {
//        List<Consultation> consultations = usersService.getConsultationsForUser(userId);
//        return ResponseEntity.ok(consultations);
//    }
}
