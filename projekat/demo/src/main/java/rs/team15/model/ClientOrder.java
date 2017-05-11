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
    @Column(name = "oid")
    private Long oid;

    @Column(name = "date")
    private Date date;

    @Column(name = "deadline")
    private Date deadline;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rsid")
    private Reservation reservation;

    @Column(name = "clientId")
    private Integer clientId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tableId")
    private TableR table;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE , CascadeType.REMOVE})
    private Set<OrderItem> items = new HashSet<OrderItem>(0);


    @JsonIgnore
    @Column(name = "waiterId")
    private Integer waiterId;

    @Column(name = "status")
    private String status;

    public ClientOrder(){
    }

    
    public ClientOrder(Long orderId, Date date, Date deadline, Reservation reservation, Integer clientId,
			TableR table, Set<OrderItem> items, String status) {
		super();
		this.oid = orderId;
		this.date = date;
		this.deadline = deadline;
		this.reservation = reservation;
		this.clientId = clientId;
		this.table = table;
		this.items = items;
		this.status = status;
	}


	public Long getOrderId() {
        return oid;
    }

    public void setOrderId(Long orderId) {
        this.oid = orderId;
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
                "orderId=" + oid +
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
