package peaksoft.springboot.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springboot.entity.House;
import peaksoft.springboot.repository.HouseRepository;
import peaksoft.springboot.service.HouseService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    @Override
    public void saveHouse(House house) {
        houseRepository.save(house);
    }

    @Override
    public House getHouseById(Long id) {
        return houseRepository.findById(id).
                orElseThrow(()-> new NullPointerException
                        ("House with id: "+id+" is not found!"));
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    @Override
    public void updateHouse(Long id, House house) {
       House house1 = houseRepository.findById(id).
                orElseThrow(()-> new NullPointerException
                        ("House with id: "+id+" is not found!"));
       house1.setHouseType(house.getHouseType());
       house1.setImageHouse(house.getImageHouse());
       house1.setAddress(house.getAddress());
       house1.setCountry(house.getCountry());
       house1.setRoom(house.getRoom());
       house1.setDescription(house.getDescription());
       house1.setPrice(house.getPrice());
       house1.setIsBooked(house.getIsBooked());
    }

    @Override
    public void deleteHouseById(Long id) {
        if (houseRepository.existsById(id)) {
            houseRepository.deleteById(id);
        }
        else throw new NullPointerException("House with id: " + id + " is not found");
    }
}
