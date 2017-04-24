package rs.team15.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "managers")
@PrimaryKeyJoinColumn(name = "m_id")
public class RestaurantManager extends User{
	@Column(name = "m_id", insertable = false, updatable = false)
    private Long mid;
	private String restaurantName;
	
	public RestaurantManager(){
		super();
	}
	
	public RestaurantManager(Long id, String email, String firstName, String lastName, String password, String rName){
    	this.id = id;
		this.email = email;
		this.fname = firstName;
		this.lname = lastName;
		this.image = "";
		this.password = password;
		this.role = "manager";
		this.verified = "no";
		this.login = "no";
		this.restaurantName = rName;
    }

	public Long getGid() {
		return mid;
	}

	public void setGid(Long smid) {
		this.mid = smid;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	@Override
	public String toString() {
		return "RestaurantManager [mid=" + mid + "]";
	}
}
