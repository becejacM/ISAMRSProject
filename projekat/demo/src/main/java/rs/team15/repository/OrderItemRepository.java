package rs.team15.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.team15.model.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Collection<OrderItem> findByOrder_Oid(Long orderId);

    Collection<OrderItem> findByRestaurantIdAndStateAndMenuItem_Type(Integer restaurantId, String state, String type);

    Collection<OrderItem> findByStateAndOrder_Table_Region_RegId(String state, Long regionId);

    OrderItem save(OrderItem orderItem);
    
    @Query ("SELECT i FROM OrderItem i WHERE itemNumber=(SELECT max(itemNumber) FROM OrderItem i)")
    OrderItem findOne();
    
    OrderItem findByItemNumber(Integer id);
}
