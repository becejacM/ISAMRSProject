package rs.team15.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "offers")
public class Offer implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ofid")
    private Long ofid;
	
	@Column(name = "status")
    private String status;
	
	@Column(name = "delivery")
    private Integer delivery;
	
	@Column(name = "version")
    private Long version;
	
	@Column(name = "warranty")
    private String warranty;
	
	@Column(name = "price")
    private Integer price;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "suid")
    private Suplier suplier;
	
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "b_id")
    private Bid bid;

	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public Offer(Long ofid, String status, Integer delivery, String warranty, Integer price, Suplier suplier) {
		super();
		this.ofid = ofid;
		this.status = status;
		this.delivery = delivery;
		this.warranty = warranty;
		this.price = price;
		this.suplier = suplier;
	}

	public Long getOfid() {
		return ofid;
	}

	public void setOfid(Long ofid) {
		this.ofid = ofid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDelivery() {
		return delivery;
	}

	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Suplier getSuplier() {
		return suplier;
	}

	public void setSuplier(Suplier suplier) {
		this.suplier = suplier;
	}
	
	

}
