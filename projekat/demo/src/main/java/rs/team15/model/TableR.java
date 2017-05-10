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
    @Column(name = "rt_id")
    private Integer tableId;

    @Column(name = "rt_datax")
    private Double datax;

    @Column(name = "rt_datay")
    private Double datay;

    @Column(name = "rt_width")
    private Double width;

    @Column(name = "rt_height")
    private Double height;

    @Column(name = "rt_positions")
    private Integer positions;

    @Column(name = "rt_table_in_restaurant_no")
    private Integer tableInRestaurantNo;

    @Column(name = "rt_deleted")
    private boolean deleted;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "rt_region_id")
    private Region region;

    @JsonIgnore
    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ClientOrder> orders = new HashSet<ClientOrder>(0);

    public TableR() {
    }
    
    

    public TableR(Integer tableId, Double datax, Double datay, Double width, Double height, Integer tableInRestaurantNo,
			Region region) {
		super();
		this.tableId = tableId;
		this.datax = datax;
		this.datay = datay;
		this.width = width;
		this.height = height;
		this.tableInRestaurantNo = tableInRestaurantNo;
		this.region = region;
	}



	public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Double getDatax() {
        return datax;
    }

    public void setDatax(Double datax) {
        this.datax = datax;
    }

    public Double getDatay() {
        return datay;
    }

    public void setDatay(Double datay) {
        this.datay = datay;
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
}
