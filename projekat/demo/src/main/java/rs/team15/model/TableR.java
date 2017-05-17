package rs.team15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurant_tables")
public class TableR {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tid")
    private Long tid;

    @Column(name = "x")
    private Double x;

    @Column(name = "y")
    private Double y;

    @Column(name = "width")
    private Double width;

    @Column(name = "height")
    private Double height;

    public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Column(name = "positions")
    private Integer positions;

    @Column(name = "rt_table_in_restaurant_no")
    private Integer tableInRestaurantNo;

    @Column(name = "deleted")
    private String deleted;
    
    @Column(name = "num_of_chairs")
    private Integer numOfChairs;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "regid")
    private Region region;

    @JsonIgnore
    @OneToMany(mappedBy = "tableRes", fetch = FetchType.LAZY)
    private Set <Reservation> reservations = new HashSet <Reservation>(0);
    
    @JsonIgnore
    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ClientOrder> orders = new HashSet<ClientOrder>(0);

    public TableR() {
    }

    public Long getTableId() {
        return tid;
    }

    public void setTableId(Long tableId) {
        this.tid = tableId;
    }

    public Double getDatax() {
        return x;
    }

    public void setDatax(Double datax) {
        this.x = datax;
    }

    public Double getDatay() {
        return y;
    }

    public void setDatay(Double datay) {
        this.y = datay;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getPositions() {
        return positions;
    }

    public void setPositions(Integer positions) {
        this.positions = positions;
    }
    
    

    public Integer getNumOfChairs() {
		return numOfChairs;
	}

	public void setNumOfChairs(Integer numOfChairs) {
		this.numOfChairs = numOfChairs;
	}

	@JsonProperty
    public Region getRegion() {
        return region;
    }

    @JsonIgnore
    public void setRegion(Region region) {
        this.region = region;
    }

    public Integer getTableInRestaurantNo() {
        return tableInRestaurantNo;
    }

    public void setTableInRestaurantNo(Integer tableInRestaurantNo) {
        this.tableInRestaurantNo = tableInRestaurantNo;
    }

    public Set<ClientOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<ClientOrder> orders) {
        this.orders = orders;
    }
    
    public String getDeleted() {
		return deleted;
	}
    
    public void setDeleted(String deleted){
    	this.deleted = deleted;
    }

	public TableR(Long tableId, Double datax, Double datay, Double width, Double height,
			Region region, Integer numOfChairs) {
		super();
		this.tid = tableId;
		this.x = datax;
		this.y = datay;
		this.width = width;
		this.height = height;
		this.region = region;
		this.numOfChairs = numOfChairs;
	}
    
    public TableR(Double datax, Double datay, Double width, Double height, Integer numOfChairs){
    	super();
		this.x = datax;
		this.y = datay;
		this.width = width;
		this.height = height;
		this.numOfChairs = numOfChairs;
    }
    
    public TableR(Double datax, Double datay, Integer tableInRestaurantNo){
    	super();
		this.x = datax;
		this.y = datay;
		this.tableInRestaurantNo = tableInRestaurantNo;
    }

}
