package rs.team15.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grades")
public class Grade {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "grid")
    private Long grid;
	
	@Column(name = "general_grade")
	private Integer generalGrade;
	
	@Column(name = "service_grade")
	private Integer serviceGrade;
	
	@Column(name = "meal_grade")
	private Integer mealGrade;
	
	@OneToOne()
	@JoinColumn(name = "eid")
	private Employee employee;
	
	/*@OneToOne()
	@JoinColumn(name = "rsid")
	private Reservation reservation;*/
	
	@OneToOne()
	@JoinColumn(name = "rid")
	private Restaurant restaurant;
	
	@OneToOne()
	@JoinColumn(name = "oid")
	private ClientOrder order;

	public Long getGrid() {
		return grid;
	}

	public void setGrid(Long grid) {
		this.grid = grid;
	}

	public Integer getGeneralGrade() {
		return generalGrade;
	}

	public void setGeneralGrade(Integer generalGrade) {
		this.generalGrade = generalGrade;
	}

	public Integer getServiceGrade() {
		return serviceGrade;
	}

	public void setServiceGrade(Integer serviceGrade) {
		this.serviceGrade = serviceGrade;
	}

	public Integer getMealGrade() {
		return mealGrade;
	}

	public void setMealGrade(Integer mealGrade) {
		this.mealGrade = mealGrade;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/*public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}*/

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public ClientOrder getOrder() {
		return order;
	}

	public void setOrder(ClientOrder order) {
		this.order = order;
	}

	public Grade(Long grid, Integer generalGrade, Integer serviceGrade, Integer mealGrade, Employee employee,
			Restaurant restaurant, ClientOrder order) {
		super();
		this.grid = grid;
		this.generalGrade = generalGrade;
		this.serviceGrade = serviceGrade;
		this.mealGrade = mealGrade;
		this.employee = employee;
		this.restaurant = restaurant;
		this.order = order;
	}
	
	public Grade(){}

}
