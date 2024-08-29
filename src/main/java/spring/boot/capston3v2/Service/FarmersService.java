package spring.boot.capston3v2.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.capston3v2.Api.ApiException;
import spring.boot.capston3v2.Model.Farmers;
import spring.boot.capston3v2.Repository.FarmersRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmersService {

    private final FarmersRepository farmersRepository;

    public List<Farmers> getAllFarmers() {
        return farmersRepository.findAll();
    }

    public void addFarmer(Farmers farmers) {
        if (farmers.getRegistration_date() == null) {
            farmers.setRegistration_date(LocalDate.now());
        }
        farmersRepository.save(farmers);
    }

    public void updateFarmer(Farmers farmers, Integer id) {
        Farmers farmers1 = farmersRepository.findFarmersById(id);
        if (farmers1 == null) {
            throw new ApiException("Farmer not found");
        }
        farmers1.setName(farmers.getName());
        farmers1.setAddress(farmers.getAddress());
        farmers1.setPhone_number(farmers.getPhone_number());
        farmers.setRegistration_date(LocalDate.now());

        farmersRepository.save(farmers1);
    }

    public void deleteFarmer(Integer id) {
        Farmers farmers1 = farmersRepository.findFarmersById(id);
        if (farmers1 == null) {
            throw new ApiException("Farmer not found");
        }
        farmersRepository.delete(farmers1);
    }
    public List<Farmers> getFarmersByLocation(String location) {
        return farmersRepository.findFarmersByLocation(location);
    }

}
