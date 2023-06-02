package peaksoft.springboot.service.serviceImpl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springboot.entity.Agency;
import peaksoft.springboot.repository.AgencyRepository;
import peaksoft.springboot.service.AgencyService;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    @Override
    public void saveAgency(Agency agency) {
        agencyRepository.save(agency);
    }

    @Override
    public Agency getAgencyById(Long id) {
        return agencyRepository.findById(id).
                orElseThrow(()-> new NullPointerException
                        ("Agency with id: "+id+" is not found!"));
    }
    @Override
    public List<Agency> getAllAgencies() {
        return agencyRepository.findAll();
    }

    @Override
    public void updateAgency(Long id, Agency agency) {
      Agency agency1 = agencyRepository.findById(id).
                orElseThrow(()-> new NullPointerException
                        ("Agency with id: "+id+" is not found!"));
      agency1.setName(agency.getName());
      agency1.setImageAgency(agency.getImageAgency());
      agency1.setEmail(agency.getEmail());
      agency1.setCountry(agency.getCountry());
      agency1.setPhoneNumber(agency.getPhoneNumber());
      agencyRepository.save(agency1);
    }
    @Override
    public void deleteAgencyById(Long id) {
        if (agencyRepository.existsById(id)) {
            agencyRepository.deleteById(id);
        }
        else throw new NullPointerException("Agency with id: " + id + " is not found");
    }

    @Override
    public boolean isAgencyNameExists(String name) {
        return agencyRepository.isAgencyNameExists(name);
    }

    @Override
    public List<Agency> searchAgency(String name, String email, String country) {
        return agencyRepository.searchAgency(name, email, country);
    }
}
