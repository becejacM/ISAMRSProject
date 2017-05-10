package rs.team15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
@PrimaryKeyJoinColumn(name = "rs_id")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rs_id")
    private Integer reservationId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "rs_restaurant_id")
    private Restaurant restaurant;

    @Column(name = "rs_duration")
    private Date reservationDateTime;

    @Column(name = "rs_length")
    private Integer length;

    public Reservation() {
        super();
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
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
                "reservationId=" + reservationId +
                ", restaurant=" + restaurant +
                ", reservationDateTime=" + reservationDateTime +
                ", length=" + length +
                '}';
    }
}

