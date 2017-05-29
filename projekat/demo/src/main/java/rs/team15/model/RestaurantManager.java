package rs.team15.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "managers")
@PrimaryKeyJoinColumn(name = "m_id")
public class RestaurantManager extends User{
	@Column(name = "m_id", insertable = false, updatable = false)
    private Long mid;
	
	@JsonIgnore
    @OneToOne
    @JoinColumn(name = "rid")
	private Restaurant restaurant;

	/*@JoinColumn(name = "rid")
    @ManyToOne(cascade = CascadeType.ALL)*/
	//private String restaurantName;

	
	public RestaurantManager(){
		super();
	}
	
	public RestaurantManager(Long id, String email, String firstName, String lastName, String password, Restaurant restaurant){
    	this.id = id;
		this.email = email;
		this.fname = firstName;
		this.lname = lastName;
		this.image = "";
		this.password = password;
		this.role = "manager";
		this.verified = "no";
		this.login = "no";
		this.restaurant = restaurant;
    }

	public Long getGid() {
		return mid;
	}

	public void setGid(Long smid) {
		this.mid = smid;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "RestaurantManager [mid=" + mid + "]";
	}
}
