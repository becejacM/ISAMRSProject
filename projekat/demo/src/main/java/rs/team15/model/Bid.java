package rs.team15.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bids")
@PrimaryKeyJoinColumn(name = "bidid")
@JsonIgnoreProperties(value = {"offers","wanted"})
public class Bid implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bidid")
    private Long bidid;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "started")
    private String started;
	
	@Column(name = "start")
    private Date start;
	
	@Column(name = "end")
    private Date end;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rid")
    private Restaurant restaurant;
	
	@OneToMany(mappedBy = "bid", fetch = FetchType.LAZY)
	private List<Offer> offers = new ArrayList<Offer>();
	
	@OneToMany(mappedBy = "bid", fetch = FetchType.LAZY)
	private List<Wanted> wanted = new ArrayList<Wanted>();

	public Bid() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bid(Long bidid,String name, String started, Date start, Date end, Restaurant restaurant, List<Offer> offers) {
		super();
		this.bidid = bidid;
		this.name = name;
		this.started = started;
		this.start = start;
		this.end = end;
		this.restaurant = restaurant;
		this.offers = offers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBidid() {
		return bidid;
	}

	public void setBidid(Long bidid) {
		this.bidid = bidid;
	}

	public String getStarted() {
		return started;
	}

	public void setStarted(String started) {
		this.started = started;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
	
	
	
}
