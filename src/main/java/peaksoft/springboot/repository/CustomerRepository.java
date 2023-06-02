package peaksoft.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.springboot.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query(value = "select case when exists(select 1 from Agency a " +
            "where a.email = :email) then true else false end", nativeQuery = true)
    boolean existsByEmail(@Param("email") String email);
}
