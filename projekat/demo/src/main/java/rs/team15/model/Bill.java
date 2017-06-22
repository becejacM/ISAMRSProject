package rs.team15.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bid")
    private Long bid;
	
	@OneToOne()
	@JoinColumn(name = "oid")
	private ClientOrder order;
	
	@OneToOne()
	@JoinColumn(name = "eid")
	private Employee employee;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	public Bill(){}

	public Bill(Long bid, ClientOrder order, Employee employee, Date date, double totalPrice) {
		super();
		this.bid = bid;
		this.order = order;
		this.employee = employee;
		this.date = date;
		this.totalPrice = totalPrice;
	}

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public ClientOrder getOrder() {
		return order;
	}

	public void setOrder(ClientOrder order) {
		this.order = order;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	

}
