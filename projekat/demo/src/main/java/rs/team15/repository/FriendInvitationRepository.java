package rs.team15.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.team15.model.FriendInvitation;
import rs.team15.model.Friendship;
import rs.team15.model.User;

public interface FriendInvitationRepository extends JpaRepository<FriendInvitation, Long>{

	@Query ("SELECT f FROM FriendInvitation f WHERE f.status = ?1 AND f.receiver.id = ?2")
    Collection<FriendInvitation> findRequests(String status, Long receiverId); //odradjeno

    @Query ("SELECT f.sender FROM FriendInvitation f WHERE f.status = ?1 AND f.receiver.id = ?2")
    Collection<User> findSenders(String status, Long userId);

    @Query ("SELECT f.receiver FROM FriendInvitation f WHERE f.status = ?1 AND f.sender.id = ?2")
    Collection<User> findReceivers(String status, Long userId);

    @Query ("SELECT f FROM FriendInvitation f WHERE f.status = ?1 AND ((f.sender.id = ?2 AND f.receiver.id = ?3) OR (f.sender.id = ?3 AND f.receiver.id = ?2))")
    FriendInvitation getFriendship(String status, Long receiverId, Long senderId) ;   //odradjeno
}