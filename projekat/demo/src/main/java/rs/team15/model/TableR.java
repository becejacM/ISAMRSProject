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

    @Column(name = "positions")
    private Integer positions;

    @Column(name = "rt_table_in_restaurant_no")
    private Integer tableInRestaurantNo;

    @Column(name = "deleted")
    private boolean deleted;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "regid")
    private Region region;

    @JsonIgnore
    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ClientOrder> orders = new HashSet<ClientOrder>(0);

    public TableR() {
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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
    
    public TableR(Long tableId, Double datax, Double datay, Double width, Double height,
			Region region) {
		super();
		this.tid = tableId;
		this.x = datax;
		this.y = datay;
		this.width = width;
		this.height = height;
		this.region = region;
	}

}
