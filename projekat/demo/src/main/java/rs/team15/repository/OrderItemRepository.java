package rs.team15.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.team15.model.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    Collection<OrderItem> findByOrder_OrderId(Integer orderId);

    Collection<OrderItem> findByRestaurantIdAndStateAndMenuItem_Type(Integer restaurantId, String state, String type);

    Collection<OrderItem> findByStateAndOrder_Table_Region_RegionId(String state, Integer regionId);
}
