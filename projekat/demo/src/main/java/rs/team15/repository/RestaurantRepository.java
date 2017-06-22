package rs.team15.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.team15.model.Region;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;

public interface RestaurantRepository extends JpaRepository <Restaurant, Long> {

    Restaurant findByName(String name);
    
    Restaurant findByRid(Long id);

    Collection<Restaurant> findByNameOrType(String name, String type);
    
    TableR save(TableR table);
    
    //Region findRegion(String region);
}
