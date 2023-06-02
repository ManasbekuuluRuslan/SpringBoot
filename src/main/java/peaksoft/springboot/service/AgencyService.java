package peaksoft.springboot.service;

import peaksoft.springboot.entity.Agency;

import java.util.List;

public interface AgencyService {
    void saveAgency(Agency agency);
    Agency getAgencyById(Long id);
    List<Agency> getAllAgencies();
    void updateAgency(Long id,Agency agency);
    void deleteAgencyById(Long id);
    boolean isAgencyNameExists(String name);
    List<Agency> searchAgency(String name,String email,String country);

}
