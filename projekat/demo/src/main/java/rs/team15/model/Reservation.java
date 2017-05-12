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

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;
    
    @Column(name = "length")
    private String length;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tid")
    private TableR tableRes;
    
    

    
    public TableR getTid() {
		return tableRes;
	}

	public void setTid(TableR tid) {
		this.tableRes = tid;
	}

	public Reservation(Long rsid, Restaurant restaurant, String date, String time, String length, TableR tid) {
		super();
		this.rsid = rsid;
		this.restaurant = restaurant;
		this.date = date;
		this.time = time;
		this.length = length;
		this.tableRes = tid;
	}

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

    public String getReservationDateTime() {
        return date;
    }

    public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setReservationDateTime(String reservationDateTime) {
        this.date = reservationDateTime;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + rsid +
                ", restaurant=" + restaurant +
                ", reservationDateTime=" + date +
                ", length=" + length +
                '}';
    }
}

