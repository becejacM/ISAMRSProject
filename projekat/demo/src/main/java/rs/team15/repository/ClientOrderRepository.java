package rs.team15.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.team15.model.ClientOrder;
import rs.team15.model.Restaurant;


public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {

    ClientOrder findByReservation_RsidAndClientId(Long reservationId, Long userId);

    Collection<ClientOrder> findByReservation_Rsid(Long reservationId);
    
    ClientOrder save(ClientOrder order);
	
	@Query ("SELECT o FROM ClientOrder o WHERE orderNumber=(SELECT max(orderNumber) FROM ClientOrder o)")
	ClientOrder findOne();
	
	ClientOrder findByOrderNumber(Integer id);
	
	@Query ("SELECT o FROM ClientOrder o WHERE o.employee.email = ?1")
	Collection<ClientOrder> findByEmployee(String email);
	
	@Query ("SELECT o FROM ClientOrder o WHERE o.restaurant.name = ?1")
	Collection<ClientOrder> findByRestaurant(String restaurant);

	Collection<ClientOrder> findByStatusAndRestaurant(String status, Restaurant r);
	
	Collection<ClientOrder> findByReservation_RsidAndTable_Tid(Long resId, Long Tid);


}