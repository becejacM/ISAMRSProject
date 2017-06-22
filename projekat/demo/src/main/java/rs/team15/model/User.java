package rs.team15.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="app_users")
@PrimaryKeyJoinColumn(name="id")
@Inheritance(strategy = InheritanceType.JOINED)
@Proxy(lazy=false) //za testove da se ne zatvara sesija
public class User implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	protected Long id;
	
	@Column(name="email", unique=true, nullable=false)
	protected String email;
	
	@Column(name = "firstName", nullable=false)
	protected String fname;

    @Column(name = "lastName", nullable=false)
    protected String lname;

    @Column(name = "password", nullable=false)
    protected String password;
    
    @Column(name = "image")
    protected String image;

    @Column(name = "role", nullable=false)
    protected String role;
    
    @Column(name = "verified")
    protected String verified;

    @Column(name = "login")
    protected String login;

    private String message;
    
    private int brojPoseta=0;
    
    public int getBrojPoseta() {
		return brojPoseta;
	}

	public void setBrojPoseta(int brojPoseta) {
		this.brojPoseta = brojPoseta;
	}

	@OneToMany(mappedBy = "userid", fetch = FetchType.LAZY)
    private Set <Reservation> reservations = new HashSet <Reservation>(0);
    
	@JsonIgnore
	public Set<Reservation> getReservations() {
		return reservations;
	}
	
	@JsonProperty
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public String token;
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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

	public String getMessage() {
			return message;
		}
	public void setMessage(String message) {
			this.message = message;
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
