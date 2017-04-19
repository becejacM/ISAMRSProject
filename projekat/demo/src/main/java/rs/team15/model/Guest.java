package rs.team15.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "guests")
@PrimaryKeyJoinColumn(name = "g_id")
public class Guest extends User{

	@Column(name = "g_id", insertable = false, updatable = false)
    private Long gid;
	
	public Guest(){
		super();
	}

	public Guest(Long id, String email, String firstName, String lastName, String password){
    	this.id = id;
		this.email = email;
		this.fname = firstName;
		this.lname = lastName;
		this.image = "";
		this.password = password;
		this.role = "guset";
		this.verified = "no";
		this.login = "no";
    }

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	@Override
	public String toString() {
		return "Guest [gid=" + gid + "]";
	}
	
	
}
