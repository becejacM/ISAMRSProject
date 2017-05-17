package rs.team15.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "waiters")
@PrimaryKeyJoinColumn(name = "w_id")
public class Waiter extends Employee{
	
	@Column(name = "w_id", insertable = false, updatable = false)
    private Long wid;
	
	public Waiter(){
		super();
	}

	public Waiter(Long id, String email, String firstName, String lastName, String password, Date birthday, String dressSize, String shoeSize){
    	this.id = id;
    	this.email = email;
		this.fname = firstName;
		this.lname = lastName;
		this.image = "";
		this.password = password;
		this.role = "waiter";
		this.verified = "no";
		this.login = "no";
		this.birthday = birthday;
		this.dressSize = dressSize;
		this.shoeSize = shoeSize;
		this.firstTime = "yes";
    }
	
	public Long getWid() {
		return wid;
	}

	public void setWid(Long wid) {
		this.wid = wid;
	}
}
