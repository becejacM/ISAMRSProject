package rs.team15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "regions")
@JsonIgnoreProperties(value = {"restaurant"})
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "regid")
    private Long regId;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rid")
    private Restaurant restaurant;

    @Column(name = "regionNo")
    private Integer regionNo;
    
    @JsonIgnore
    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private Set <Employee> employees = new HashSet <Employee>(0);

    public Region(Long regionId, String name, String color, Restaurant restaurant, Integer regionNo,
			Set<TableR> tables) {
		super();
		this.regId = regionId;
		this.name = name;
		this.color = color;
		this.restaurant = restaurant;
		this.regionNo = regionNo;
		this.tables = tables;
	}

	@JsonIgnore
    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private Set<TableR> tables = new HashSet <TableR>(0);

    public Region() {
    }

    public Long getRegionId() {
        return regId;
    }

    public void setRegionId(Long regionId) {
        this.regId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @JsonIgnore
    public Set <TableR> getTables() {
        return tables;
    }

    @JsonIgnore
    public void setTables(Set <TableR> tables) {
        this.tables = tables;
    }

    public Integer getRegionNo() {
        return regionNo;
    }

    public void setRegionNo(Integer regionNo) {
        this.regionNo = regionNo;
    }

    @JsonProperty
	public Set<Employee> getEmployees() {
		return employees;
	}

    @JsonIgnore
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Long getRegId() {
		return regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}
    
    
    
}

