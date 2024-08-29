package spring.boot.capston3v2.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.capston3v2.Api.ApiException;
import spring.boot.capston3v2.Model.Farmers;
import spring.boot.capston3v2.Model.Nurseries;
import spring.boot.capston3v2.Model.Plants;
import spring.boot.capston3v2.Repository.FarmersRepository;
import spring.boot.capston3v2.Repository.NurseriesRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NurseriesService {
    private final NurseriesRepository nurseriesRepository;
    private final FarmersRepository farmersRepository;

    public List<Nurseries> getAllNurseries() {

        return nurseriesRepository.findAll();
    }
    public void addNurseries(Nurseries nurseries, Integer farmer_id){
        Farmers farmer = farmersRepository.findFarmersById(farmer_id);
        if(farmer==null){
            throw new ApiException("Cannot Assign Nurseries to Farmer!");
        }
        nurseries.setFarmers(farmer);
        nurseriesRepository.save(nurseries);
    }
    public void updateNurseries(Nurseries nurseries,Integer id){
        Nurseries nurseries1 = nurseriesRepository.findNurseriesById(id);
        if (nurseries1 == null){
            throw new ApiException("Nurseries not found");
        }
        nurseries1.setName(nurseries.getName());
        nurseries1.setAddress(nurseries.getAddress());
        nurseries1.setPhone_number(nurseries.getPhone_number());
        nurseries1.setTypeOfPlants(nurseries.getTypeOfPlants());
        nurseries1.setRegistration_date(nurseries.getRegistration_date());

        nurseriesRepository.save(nurseries1);
    }
    public void deleteNurseries(Integer id){
        Nurseries nurseries = nurseriesRepository.findNurseriesById(id);
        if(nurseries == null){
            throw new ApiException("Nurseries not found");
        }
        nurseriesRepository.delete(nurseries);
    }

    //Assign list of plants to one Nurserie
   public Set<Plants> getListOfPlantsToNurseries(Integer nurseries_id){
        Nurseries nurseries = nurseriesRepository.findNurseriesById(nurseries_id);
        if(nurseries == null){
            throw new ApiException("Cannot Assign Plants to Nurseries!");
        }
        if(nurseries.getPlants().isEmpty()){
            throw new ApiException("Plants not found, There are no plants assigned!");
        }
        return nurseries.getPlants();
   }
//    public void updatePlantTypeForNursery(Integer nurseryId, String newPlantType) {
//        Nurseries nursery = nurseriesRepository.findNurseriesById(nurseryId);
//        if (nursery == null) {
//            throw new ApiException("Nursery not found");
//        }
//        nursery.setTypeOfPlants(newPlantType);
//        nurseriesRepository.save(nursery);
//    }
    public Set<Nurseries> getNurseriesWithAvailablePlantStock(Integer plant_id) {
        Set <Nurseries> nurseries = nurseriesRepository.findNurseriesWithAvailableStockForPlant(plant_id);
        if (nurseries.isEmpty()) {
            throw new ApiException("No nurseries found with available stock for the specified plant.");
        }
        return nurseries;
    }

    public List<Nurseries> getNurseriesByLocation(String location) {
        return nurseriesRepository.findNurseriesByLocation(location);
    }


//    public Set<Plants> getAllPlantsByNursery(Integer nurseryId) {
//        Nurseries nursery = nurseriesRepository.findNurseriesById(nurseryId);
//
//        if (nursery == null) {
//            throw new ApiException("Nursery not found");
//        }
//
//        return nursery.getPlants();
//    }

}
