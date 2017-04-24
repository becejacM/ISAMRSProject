package rs.team15.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "e_id")
public class Employee extends User {

	@Column(name = "e_id", insertable = false, updatable = false)
    private Long eid;
	
	@Column(name = "birthday", nullable=false)
	protected Date birthday;
	
	@Column(name = "dressSize", nullable=false)
	protected String dressSize;
	
	@Column(name = "shoeSize", nullable=false)
	protected String shoeSize;
	
	@Column(name = "firstTime", nullable=false)
	protected String firstTime;
	
	public Employee(){
		super();
	}

	public Employee(Long id, String email, String firstName, String lastName, String password, Date birthday, String dressSize, String shoeSize){
    	this.id = id;
    	this.email = email;
		this.fname = firstName;
		this.lname = lastName;
		this.image = "";
		this.password = password;
		this.verified = "no";
		this.login = "no";
		this.firstTime = "yes";
    }
	
	public Employee(Long id, String email, String firstName, String lastName, String password, Date birthday, String dressSize, String shoeSize, String role){
    	this.id = id;
    	this.email = email;
		this.fname = firstName;
		this.lname = lastName;
		this.image = "";
		this.password = password;
		this.verified = "no";
		this.login = "no";
		this.role = role;
		this.firstTime = "yes";
    }
	
	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDressSize() {
		return dressSize;
	}

	public void setDressSize(String dressSize) {
		this.dressSize = dressSize;
	}

	public String getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(String shoeSize) {
		this.shoeSize = shoeSize;
	}

	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}
	
	
	
	
}
