package rs.team15.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "wanted")
public class Wanted implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wid")
    private Long wid;

	@Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;
    
    @Column(name = "type")
    private String type;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "b_id")
    private Bid bid;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public Wanted(Long wid, String name, String value) {
		super();
		this.wid = wid;
		this.name = name;
		this.value = value;
	}

	public Wanted() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getWid() {
		return wid;
	}

	public void setWid(Long wid) {
		this.wid = wid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
    
    
}
