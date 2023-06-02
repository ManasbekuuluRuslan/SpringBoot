package peaksoft.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.springboot.entity.House;

public interface HouseRepository extends JpaRepository<House,Long> {


}
