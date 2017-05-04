package rs.team15.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.team15.model.Friendship;
import rs.team15.model.User;

public interface FriendshipRepository extends JpaRepository<Friendship, Long>{

	@Query ("SELECT f.sender FROM Friendship f WHERE f.status = ?1 AND f.receiver.id = ?2")
    Collection<User> findFriendshipRequests(String status, Long receiverId);

    @Query ("SELECT f.sender FROM Friendship f WHERE f.status = ?1 AND f.receiver.id = ?2")
    Collection<User> findFriendsSenders(String status, Long userId);

    @Query ("SELECT f.receiver FROM Friendship f WHERE f.status = ?1 AND f.sender.id = ?2")
    Collection<User> findFriendsReceivers(String status, Long userId);

    @Query ("SELECT f FROM Friendship f WHERE f.status = ?1 AND ((f.sender.id = ?2 AND f.receiver.id = ?3) OR (f.sender.id = ?3 AND f.receiver.id = ?2))")
    Friendship getFriendship(String status, Long receiverId, Long senderId);
}
