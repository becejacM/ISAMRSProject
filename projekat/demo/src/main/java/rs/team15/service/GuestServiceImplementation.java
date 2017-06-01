package rs.team15.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.FriendInvitation;
import rs.team15.model.Friendship;
import rs.team15.model.Guest;
import rs.team15.model.Reservation;
import rs.team15.model.User;
import rs.team15.repository.FriendInvitationRepository;
import rs.team15.repository.FriendshipRepository;
import rs.team15.repository.GuestRepository;
import rs.team15.repository.UserRepository;

@Service
public class GuestServiceImplementation implements GuestService{

	 @Autowired
	 GuestRepository guestRepository;

	 @Autowired
	 UserRepository userRepository;
	 
	 @Autowired
	 FriendshipRepository friendshipRepository;
	 
	 @Autowired
	 FriendInvitationRepository friendInvitationRepository;
	@Override
	public Guest getGuest(Long id) {
		// TODO Auto-generated method stub
		return guestRepository.findOne(id);
	}

	@Override
	public User create(Guest guest) {
		// TODO Auto-generated method stub
		return guestRepository.save(guest);
	}

	
	@Override
	public Collection<Guest> findAll() {
		// TODO Auto-generated method stub
		return guestRepository.findAll();
	}
	
	@Override
	public Collection<User> findFriendsIAccept(Long id) {
		// TODO Auto-generated method stub
		
		return friendshipRepository.findSenders("accept", id);
	}
	
	@Override
	public Collection<User> findFriendsIAdd(Long id) {
		// TODO Auto-generated method stub
		
		return friendshipRepository.findReceivers("accept", id);
	}

	@Override
	public Collection<User> findReq(Long id) {
		// TODO Auto-generated method stub
		return friendshipRepository.findRequests("pending", id);
	}
	
	@Override
	public Collection<Guest> getGuestByFirstName(String fname) {
		// TODO Auto-generated method stub
		return userRepository.findByFirstName(fname);
	}
	
	
	@Override
	public Friendship addFriend(Long senderId, Long receiverId) {
		// TODO Auto-generated method stub
		Friendship f = friendshipRepository.getFriendship("pending", senderId, receiverId);
		if(f!=null){
			return null;
		}
		Friendship ff = friendshipRepository.getFriendship("accept", senderId, receiverId);
		if(ff!=null){
			return null;
		}
		Friendship fs = new Friendship();
        fs.setSender(guestRepository.findOne(senderId));
        fs.setReceiver(guestRepository.findOne(receiverId));
        fs.setStatus("pending");

        
        return friendshipRepository.save(fs);
		
	}
	
	@Override
	public Friendship accept(Long senderId, Long receiverId) {
		// TODO Auto-generated method stub
		Friendship fs = friendshipRepository.getFriendship("pending", senderId, receiverId);
        fs.setStatus("accept");

        return friendshipRepository.save(fs);
		
	}
	
	@Override
	public Friendship reject(Long senderId, Long receiverId) {
		// TODO Auto-generated method stub
		Friendship fs = friendshipRepository.getFriendship("pending", senderId, receiverId);
        fs.setStatus("reject");

        friendshipRepository.delete(fs);
        return null;
		
	}
	
	@Override
	public Friendship deleteF(Long senderId, Long receiverId) {
		// TODO Auto-generated method stub
		Friendship fs = friendshipRepository.getFriendship("accept", senderId, receiverId);
		if(fs!=null){
			friendshipRepository.delete(fs);
		}

        return null;
		
	}

	@Override
	public FriendInvitation addFriendInvite(Long senderId, Long receiverId, Reservation r) {
		// TODO Auto-generated method stub
		/*FriendInvitation f = friendInvitationRepository.getFriendship("pending", senderId, receiverId);
		if(f!=null){
			return null;
		}
		FriendInvitation ff = friendInvitationRepository.getFriendship("accept", senderId, receiverId);
		if(ff!=null){
			return null;
		}*/
		FriendInvitation fs = new FriendInvitation();
        fs.setSender(guestRepository.findOne(senderId));
        fs.setReceiver(guestRepository.findOne(receiverId));
        fs.setStatus("pending");
        fs.setReservation(r);

        return friendInvitationRepository.save(fs);
	}

	@Override
	public Collection<FriendInvitation> findFI(Long id) {
		// TODO Auto-generated method stub
		return friendInvitationRepository.findRequests("pending", id);
	}

	@Override
	public Collection<FriendInvitation> getByReservation_rsid(Long resid) {
		// TODO Auto-generated method stub
		return friendInvitationRepository.getByReservation_rsid(resid);
	}

	@Override
	public FriendInvitation acceptInvite(Long fid) {
		// TODO Auto-generated method stub
		FriendInvitation fs = friendInvitationRepository.getByFid(fid);
        
        fs.setStatus("accept");

        return friendInvitationRepository.save(fs);
	}

	@Override
	public FriendInvitation rejectInvite(Long fid) {
		// TODO Auto-generated method stub
		FriendInvitation fs = friendInvitationRepository.getByFid(fid);
        fs.setStatus("reject");

        return friendInvitationRepository.save(fs);
	}

	@Override
	public Collection<FriendInvitation> findFIAccept(String status, Long senderid) {
		// TODO Auto-generated method stub
		return friendInvitationRepository.getFIAccept(status, senderid);
	}
}
