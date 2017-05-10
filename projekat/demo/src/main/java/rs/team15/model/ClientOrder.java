package rs.team15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "co_id")
    private Integer orderId;

    @Column(name = "co_date")
    private Date date;

    @Column(name = "co_deadline")
    private Date deadline;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "co_reservation_id")
    private Reservation reservation;

    @Column(name = "co_client_id")
    private Integer clientId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "co_table_id")
    private TableR table;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE , CascadeType.REMOVE})
    private Set<OrderItem> items = new HashSet<OrderItem>(0);


    @JsonIgnore
    @Column(name = "co_waiter_id")
    private Integer waiterId;

    @Column(name = "co_status")
    private String status;

    public ClientOrder(){
    }

    
    public ClientOrder(Integer orderId, Date date, Date deadline, Reservation reservation, Integer clientId,
			TableR table, Set<OrderItem> items, String status) {
		super();
		this.orderId = orderId;
		this.date = date;
		this.deadline = deadline;
		this.reservation = reservation;
		this.clientId = clientId;
		this.table = table;
		this.items = items;
		this.status = status;
	}


	public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @JsonIgnore
    public Set<OrderItem> getItems() {
        return items;
    }

    @JsonProperty
    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public Integer getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Integer waiterId) {
        this.waiterId = waiterId;
    }

    @Override
    public String toString() {
        return "ClientOrder{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", deadline=" + deadline +
                ", table=" + table +
                ", items=" + items +
                '}';
    }

    @JsonIgnore
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
