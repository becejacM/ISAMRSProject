package rs.team15.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.team15.model.Restaurant;

public interface RestaurantRepository extends JpaRepository <Restaurant, Integer> {

    Restaurant findRestaurantByName(String name);

    Collection<Restaurant> findRestaurantByNameContainsOrTypeStartsWithAllIgnoreCase(String name, String type);
}
