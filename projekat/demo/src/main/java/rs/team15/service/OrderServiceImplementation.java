package rs.team15.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.ClientOrder;
import rs.team15.model.OrderItem;
import rs.team15.repository.ClientOrderRepository;
import rs.team15.repository.OrderItemRepository;

@Service
public class OrderServiceImplementation implements OrderService {

	@Autowired
	ClientOrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ClientOrder create(ClientOrder order) {
		ClientOrder o = orderRepository.findOne();
		int i = 1;
		if(o == null){
			order.setOrderNumber(i);
		}
		else {
			i = o.getOrderNumber() + 1;
			logger.info("<< number: {}", i);
			order.setOrderNumber(i);
		}
		return orderRepository.save(order);
	}

	@Override
	public OrderItem addNew(OrderItem item) {
		
		return orderItemRepository.save(item);
	}

	@Override
	public ClientOrder update(ClientOrder order) {
		return orderRepository.save(order);
	}

	@Override
	public ClientOrder find(Integer id) {
		return orderRepository.findByOrderNumber(id);
	}

	@Override
	public Collection<ClientOrder> findByEmployee(String email) {
		return orderRepository.findByEmployee(email);
	}

	@Override
	public Collection<ClientOrder> findByRestaurant(String restaurant) {
		return orderRepository.findByRestaurant(restaurant);
	}

}
