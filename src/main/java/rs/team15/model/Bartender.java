package rs.team15.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "bartenders")
@PrimaryKeyJoinColumn(name = "b_id")
public class Bartender extends Employee{
	
	@Column(name = "b_id", insertable = false, updatable = false)
    private Long bid;
	
	public Bartender(){
		super();
	}

	public Bartender(Long id, String email, String firstName, String lastName, String password, Date birthday, String dressSize, String shoeSize){
    	this.id = id;
    	this.email = email;
		this.fname = firstName;
		this.lname = lastName;
		this.image = "";
		this.password = password;
		this.role = "bartender";
		this.verified = "no";
		this.login = "no";
		this.birthday = birthday;
		this.dressSize = dressSize;
		this.shoeSize = shoeSize;
		this.firstTime = "yes";
    }
	
	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

}
