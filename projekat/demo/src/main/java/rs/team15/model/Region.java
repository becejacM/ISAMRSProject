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
    @Column(name = "rid")
    private Integer regionId;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rr_restaurant_id")
    private Restaurant restaurant;

    @Column(name = "rr_region_no")
    private Integer regionNo;

    public Region(Integer regionId, String name, String color, Restaurant restaurant, Integer regionNo,
			Set<TableR> tables) {
		super();
		this.regionId = regionId;
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

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
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
}

