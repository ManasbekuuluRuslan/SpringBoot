package peaksoft.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.springboot.entity.Agency;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agency,Long> {
    @Query("select count (a) from Agency a where a.name = :name")
    boolean isAgencyNameExists(String name);

    @Query("select a from Agency a where lower(a.name) like lower(concat('%', :name, '%')) " +
            "and lower(a.email) like lower(concat('%', :email, '%')) " +
            "and lower(a.country) like lower(concat('%', :country, '%')) ")
    List<Agency> searchAgency(
            @Param("name") String name,
            @Param("email") String email,
            @Param("country") String country);
}
