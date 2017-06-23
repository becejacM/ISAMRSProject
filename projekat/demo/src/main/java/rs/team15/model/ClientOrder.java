package rs.team15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client_orders")
public class ClientOrder implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oid")
    private Long oid;

    @Column(name = "dateOf")
    private Date dateOf;

    @Column(name = "deadline")
    private Date deadline;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "rsid")
    private Reservation reservation;

    @Column(name = "clientId")
    private Integer clientId;

    //@JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "tableId")
    private TableR table;

    @JsonIgnoreProperties(value={"order"})
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE , CascadeType.REMOVE})
    private Set<OrderItem> items = new HashSet<OrderItem>(0);


    @ManyToOne()
    @JoinColumn(name = "eid")
	private Employee employee;
    
    @Column(name = "total_price")
	private double totalPrice;
    
    @Column(name = "order_number")
	private Integer orderNumber;
    
    @ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "rid")
	private Restaurant restaurant;

    @Column(name = "status")
    private String status;
    
    @Version
    @Column(name = "version")
    private Long version ;

    public ClientOrder(){
    }

    
    /*public ClientOrder(Date date, Reservation reservation, Integer clientId,
			 Set<OrderItem> items, String status) {
		super();
		//this.oid = orderId;
		this.date = date;
		//this.deadline = deadline;
		this.reservation = reservation;
		this.clientId = clientId;
		//this.table = table;
		this.items = items;
		this.status = status;
	}*/

    

	public Long getOrderId() {
        return oid;
    }

    public Long getVersion() {
		return version;
	}


	public void setVersion(Long version) {
		this.version = version;
	}


	public void setOrderId(Long orderId) {
        this.oid = orderId;
    }

    public Date getDate() {
        return dateOf;
    }

    public void setDate(Date date) {
        this.dateOf = date;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TableR getTable() {
        return table;
    }

    public void setTable(TableR table) {
        this.table = table;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonManagedReference
    //@JsonIgnore
    public Set<OrderItem> getItems() {
        return items;
    }

    @JsonProperty
    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }
    
    

    public Long getOid() {
		return oid;
	}


	public void setOid(Long oid) {
		this.oid = oid;
	}


	public Employee getEmployee() {
		return employee;
	}

	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Integer getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	


	@Override
    public String toString() {
        return "ClientOrder{" +
                "orderId=" + oid +
                ", date=" + dateOf +
                ", deadline=" + deadline +
                ", table=" + table +
                ", items=" + items +
                '}';
    }

    //@JsonIgnore
    public Reservation getReservation() {
        return reservation;
    }

    @JsonProperty
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
