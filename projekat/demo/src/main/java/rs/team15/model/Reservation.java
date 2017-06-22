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
    
    

	public String getNameRest() {
		return nameRest;
	}

	public void setNameRest(String nameRest) {
		this.nameRest = nameRest;
	}

	@Column(name = "dateOf")
    private String dateOf;

    @Column(name = "timeOf")
    private String timeOf;
    
    @Column(name = "length")
    private String length;
    
    @Column(name = "status")
    private String status;
    
    /*@Version
	private Long version;
    
    public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}*/

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
		this.dateOf = date;
		this.timeOf = time;
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
        return dateOf;
    }

    public String getTime() {
		return timeOf;
	}

	public void setTime(String time) {
		this.timeOf = time;
	}

	public void setReservationDateTime(String reservationDateTime) {
        this.dateOf = reservationDateTime;
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
                ", reservationDateTime=" + dateOf +
                ", length=" + length +
                '}';
    }
}

