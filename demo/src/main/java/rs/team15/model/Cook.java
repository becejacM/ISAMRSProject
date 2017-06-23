package rs.team15.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cooks")
@PrimaryKeyJoinColumn(name = "c_id")
public class Cook extends Employee{
	
	@Column(name = "c_id", insertable = false, updatable = false)
    private Long cid;
	
	public Cook(){
		super();
	}

	public Cook(Long id, String email, String firstName, String lastName, String password, Date birthday, String dressSize, String shoeSize){
    	this.id = id;
    	this.email = email;
		this.fname = firstName;
		this.lname = lastName;
		this.image = "";
		this.password = password;
		this.role = "cook";
		this.verified = "no";
		this.login = "no";
		this.birthday = birthday;
		this.dressSize = dressSize;
		this.shoeSize = shoeSize;
		this.firstTime = "yes";
    }
	
	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

}
