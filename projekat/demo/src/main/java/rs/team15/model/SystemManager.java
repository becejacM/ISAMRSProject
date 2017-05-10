package rs.team15.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "sysManagers")
@PrimaryKeyJoinColumn(name = "sm_id")
public class SystemManager extends User{
	
	@Column(name = "sm_id", insertable = false, updatable = false)
    private Long smid;
	
	public SystemManager(){
		super();
	}
	
	public SystemManager(Long id, String email, String firstName, String lastName, String password){
    	this.id = id;
		this.email = email;
		this.fname = firstName;
		this.lname = lastName;
		this.image = "";
		this.password = password;
		this.role = "system_manager";
		this.verified = "no";
		this.login = "no";
    }

	public Long getGid() {
		return smid;
	}

	public void setGid(Long smid) {
		this.smid = smid;
	}

	@Override
	public String toString() {
		return "SystemManager [smid=" + smid + "]";
	}
}
