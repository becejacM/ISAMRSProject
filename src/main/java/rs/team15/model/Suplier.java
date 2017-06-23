package rs.team15.model;

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
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "supliers")
@PrimaryKeyJoinColumn(name = "supid")
@JsonIgnoreProperties(value = {"restaurants"})
public class Suplier extends User{

	@Column(name = "supid", insertable = false, updatable = false)
    private Long supid;
	
	@Column(name = "name", nullable=false)
	protected String name;
	
	@Column(name = "firstTime", nullable=false)
	protected String firstTime;
	

	@OneToMany(mappedBy = "suplier", fetch = FetchType.LAZY)
	private List<Restaurant> restaurants = new ArrayList<Restaurant>();


	public Suplier() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Suplier(Long id, String email, String firstName, String lastName, String password, String name,List<Restaurant> restaurants){
    	this.id = id;
    	this.email = email;
		this.fname = firstName;
		this.lname = lastName;
		this.image = "";
		this.password = password;
		this.role = "suplier";
		this.verified = "no";
		this.login = "no";
		this.name = name;
		this.firstTime = "yes";
		this.restaurants = restaurants;
    }
	
	public Suplier(Long id, String email, String firstName, String lastName, String password, Long supid, String name,
			String firstTime, List<Restaurant> restaurants) {
		super(id, email, firstName, lastName, password);
		this.supid = supid;
		this.name = name;
		this.firstTime = firstTime;
		this.restaurants = restaurants;
	}


	public Long getSupid() {
		return supid;
	}


	public void setSupid(Long supid) {
		this.supid = supid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFirstTime() {
		return firstTime;
	}


	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}


	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	
	
}
