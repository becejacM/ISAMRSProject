package rs.team15.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="app_users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	@Column(name = "firstName", nullable=false)
    private String fname;

    @Column(name = "lastName", nullable=false)
    private String lname;

    @Column(name = "image")
    private String image;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "role", nullable=false)
    private String role;
    
    @Column(name = "verified")
    private String verified;

    @Column(name = "login")
    private String login;

    public User(Long id, String email, String firstName, String lastName, String password){
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
	public User(Long id, String email, String firstName, String lastName, String image, String password, String role,
			String verified, String login) {
		super();
		this.id = id;
		this.email = email;
		this.fname = firstName;
		this.lname = lastName;
		this.image = image;
		this.password = password;
		this.role = role;
		this.verified = verified;
		this.login = login;
	}

	public User(Long id, String email, String firstName, String lastName, String password, String role) {
		super();
		this.id = id;
		this.email = email;
		this.fname = firstName;
		this.lname = lastName;
		this.password = password;
		this.role = role;
		this.verified = "no";
		this.login = "no";
	}
	
	public User(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return fname;
	}

	public void setFirstName(String firstName) {
		this.fname = firstName;
	}

	public String getLastName() {
		return lname;
	}

	public void setLastName(String lastName) {
		this.lname = lastName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String isVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public String isLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + fname + ", lastName=" + lname
				+ ", image=" + image + ", password=" + password + ", role=" + role + ", verified=" + verified
				+ ", login=" + login + "]";
	}
	
	
    
    

}
