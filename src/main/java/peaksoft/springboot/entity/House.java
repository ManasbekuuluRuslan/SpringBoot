package peaksoft.springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.springboot.enums.HouseType;
@Entity
@Table(name = "houses")
@Setter
@Getter
@NoArgsConstructor
public class House {
    @Id
    @GeneratedValue(generator = "house_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "house_gen",sequenceName = "house_seq",allocationSize = 1)
    private Long id;
    @Column(name = "house_type")
    private HouseType houseType;
    private String imageHouse;
    private String address;
    private double price;
    private int room;
    private String country;
    private String description;
    private String isBooked;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private Agency agency;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},mappedBy = "house")
    private Booking booking;
    public House(HouseType houseType, String address, double price,
                 int room, String country, String description, String isBooked) {
        this.houseType = houseType;
        this.address = address;
        this.price = price;
        this.room = room;
        this.country = country;
        this.description = description;
        this.isBooked = isBooked;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", houseType=" + houseType +
                ", imageHouse='" + imageHouse + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", room=" + room +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", isBooked='" + isBooked + '\'' +
                ", agency=" + agency +
                '}';
    }
}
