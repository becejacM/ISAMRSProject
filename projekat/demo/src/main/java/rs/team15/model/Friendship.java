package rs.team15.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "friendships")
public class Friendship implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fid")
    private Long fid;

    @JoinColumn(name = "first")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User sender;

    @JoinColumn(name = "second")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User receiver;

    @Column(name = "status")
    private String status;
    
    public Friendship(){
    	super();
    }

	public Friendship(rs.team15.model.User sender, rs.team15.model.User receiver, String status) {
		super();
		this.fid = fid;
		this.sender = sender;
		this.receiver = receiver;
		this.status = status;
	}

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Friendship [fid=" + fid + ", sender=" + sender + ", receiver=" + receiver + ", status=" + status + "]";
	}
    
    
}
