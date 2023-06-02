package peaksoft.springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.springboot.enums.Gender;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(generator = "customer_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer_gen",sequenceName = "customer_seq",allocationSize = 1)
    private Long id;
    private String name;
    @Column(name = "sur_name")
    private String surname;
    @Column(unique = true)
    private String email;
    private Gender gender;
    @Column(name = "phone_number")
    private Integer phoneNumber;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy = "customerList")
    private List<Agency> agencyList;
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},mappedBy = "customer")
    private List<Booking> bookingList;

    public Customer(String name, String surname, String email,
                    Gender gender, Integer phoneNumber, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", phoneNumber=" + phoneNumber +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
