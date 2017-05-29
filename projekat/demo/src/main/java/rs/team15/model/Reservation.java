package rs.team15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "nameRest")
    private String nameRest;
    
    public String getId() {
		return nameRest;
	}

	public void setId(String id) {
		this.nameRest = id;
	}

	@Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;
    
    @Column(name = "length")
    private String length;
    
    @Column(name = "status")
    private String status;
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUid() {
		return userid;
	}

	public void setUid(User uid) {
		this.userid = uid;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tid")
    private TableR tableRes;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uid")
    private User userid;

    @JsonIgnore
    public TableR getTid() {
		return tableRes;
	}
    @JsonProperty
	public void setTid(TableR tid) {
		this.tableRes = tid;
	}

    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY)
    private Set <FriendInvitation> invitations = new HashSet <FriendInvitation>(0);
    
	@JsonIgnore
	public Set<FriendInvitation> getInvitations() {
		return invitations;
	}
	
	@JsonProperty
	public void setInvitations(Set<FriendInvitation> invitations) {
		this.invitations = invitations;
	}
	public Reservation(Restaurant restaurant, String date, String time, String length, TableR tid) {
		super();
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

    //@JsonIgnore
    @JsonIgnore
    public Restaurant getRestaurant() {
        return restaurant;
    }

    @JsonProperty
    //@JsonIgnore
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

