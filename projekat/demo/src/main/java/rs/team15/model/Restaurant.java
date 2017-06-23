package rs.team15.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurants")
@JsonIgnoreProperties(value = {"systemManager"})
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rid")
    private Long rid;

	@Column(name = "name")
    private String name;

    @Column(name = "info")
    private String info;

    @Column(name = "type")
    private String type;

    @Column(name = "startTime")
    private Integer startTime;

    @Column(name = "endTime")
    private Integer endTime;

    @Column(name = "address")
    private String address;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sm_id")
    private SystemManager systemManager;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private Set <MenuItem> menuItemMenu = new HashSet <MenuItem>(0);

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private Set <Region> regions = new HashSet <Region>(0);
    
    /*@JsonIgnore
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private Set <Employee> employees = new HashSet <Employee>(0);*/

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private Set <Reservation> reservations = new HashSet <Reservation>(0);
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "supid")
    private Suplier suplier;
    
	public Suplier getSuplier() {
		return suplier;
	}

	public void setSuplier(Suplier suplier) {
		this.suplier = suplier;
	}

	@JsonIgnore
	public Set<Reservation> getReservations() {
		return reservations;
	}
	
	@JsonProperty
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
    public Restaurant() {
    }


    public Long getRestaurantId() {
        return rid;
    }

    public void setRestaurantId(Long restaurantId) {
        this.rid = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    //@JsonIgnore
    public SystemManager getSystemManager() {
        return systemManager;
    }

    //@JsonProperty
    public void setSystemManager(SystemManager systemManager) {
        this.systemManager = systemManager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //@JsonProperty
    public Set <MenuItem> getMenuItemMenu() {
        return menuItemMenu;
    }

    //@JsonBackReference
    public void setMenuItemMenu(Set <MenuItem> menuItemMenu) {
        this.menuItemMenu = menuItemMenu;
    }

    @JsonIgnore
    public Set <Region> getRegions() {
        return regions;
    }
    
    @JsonIgnore
    public void setRegions(Set <Region> regions) {
        this.regions = regions;
    }


    /*@JsonProperty
	public Set<Employee> getEmployees() {
		return employees;
	}

    @JsonIgnore
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}*/

	//public Restaurant(Long restaurantId, String name, Integer startTime, Integer endTime, Set<MenuItem> menuItemMenu,

    public Restaurant(Long id,String name, Integer startTime, Integer endTime, Set<MenuItem> menuItemMenu,

			Set<Region> regions) {
		super();
		this.rid= id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.menuItemMenu = menuItemMenu;
		this.regions = regions;
	}
	

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
}
