package peaksoft.springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "agencies")
@Getter
@Setter
@NoArgsConstructor
public class Agency {
    @Id
    @GeneratedValue(generator = "agency_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "agency_gen",sequenceName = "agency_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String imageAgency;
    private String country;
    @Column(name = "phone_number")
    private int phoneNumber;
    private String email;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Customer> customerList;
    @OneToMany(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE},mappedBy = "agency")
    private List<House> houseList;
    public Agency(String name, String country, int phoneNumber, String email) {
        this.name = name;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageAgency='" + imageAgency + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }
}