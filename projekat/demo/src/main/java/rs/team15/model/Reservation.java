package rs.team15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
@PrimaryKeyJoinColumn(name = "rsid")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rsid")
    private Long rsid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "rid")
    private Restaurant restaurant;

    @Column(name = "duration")
    private Date reservationDateTime;

    @Column(name = "length")
    private Integer length;

    public Reservation() {
        super();
    }

    public Long getReservationId() {
        return rsid;
    }

    public void setReservationId(Long reservationId) {
        this.rsid = reservationId;
    }

    @JsonIgnore
    public Restaurant getRestaurant() {
        return restaurant;
    }

    @JsonProperty
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(Date reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + rsid +
                ", restaurant=" + restaurant +
                ", reservationDateTime=" + reservationDateTime +
                ", length=" + length +
                '}';
    }
}

