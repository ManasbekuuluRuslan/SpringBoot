package peaksoft.springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "booking")
@Setter
@Getter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(generator = "booking_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "booking_gen",sequenceName = "booking_seq",allocationSize = 1)
//    private Long id;
//    @Column(name = "customer_id")
//    private int customerId;
//    @Column(name = "house_id")
//    private int houseId;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE})
    private Customer customer;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
    private House house;
//    public Booking(int customerId, int houseId) {
//        this.customerId = customerId;
//        this.houseId = houseId;
//    }

}
