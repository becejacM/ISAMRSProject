package rs.team15.model;

import java.util.Date;

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

@Entity
@Table(name = "shifts")
public class Shift {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shid")
    private Long shid;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "startsAt")
	private Date startsAt;
	
	@Column(name = "endsAt")
	private Date endsAt;
	
	@Column(name = "draggable")
	private boolean draggable;
	
	@Column(name = "resizable")
	private boolean resizable;
	
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "eid")
    private Employee employee;

	public Shift() {
		super();
	}

	

	public Date getStartsAt() {
		return startsAt;
	}



	public void setStartsAt(Date startsAt) {
		this.startsAt = startsAt;
	}



	public Date getEndsAt() {
		return endsAt;
	}



	public void setEndsAt(Date endsAt) {
		this.endsAt = endsAt;
	}



	public boolean isDraggable() {
		return draggable;
	}



	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}



	public boolean isResizable() {
		return resizable;
	}



	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}



	public Shift(Long shid, String color, Date startsAt, Date endsAt, boolean draggable, boolean resizable,
			Employee employee) {
		super();
		this.shid = shid;
		this.color = color;
		this.startsAt = startsAt;
		this.endsAt = endsAt;
		this.draggable = draggable;
		this.resizable = resizable;
		this.employee = employee;
	}



	public Long getShid() {
		return shid;
	}

	public void setShid(Long shid) {
		this.shid = shid;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
}
