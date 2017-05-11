package rs.team15.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.team15.model.MenuItem;

public interface MenuItemRepository extends JpaRepository <MenuItem, Long> {

    Collection <MenuItem> findByMid(Integer id);

    Collection <MenuItem> findByTypeAndDeletedAndMid(String type, boolean deleted, Long id);
}