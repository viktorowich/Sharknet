package berlin.htw.schneider.viktor.sharknet.api;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by timol on 16.05.2016.
 */
public class ImplComment implements Comment{

	//ToDo: Implement - File - Mine Type

	String comment;
	Feed reffeed;
	Timestamp datetime;
	Contact sender;

	/**
	 * This constructor is used to construct new Feeds writen by a user
	 * @param comment
	 * @param sender
	 * @param reffeed
     */
	public ImplComment(String comment, Contact sender, Feed reffeed){
		this.comment = comment;
		this.sender = sender;
		this.reffeed = reffeed;
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		datetime = new Timestamp(now.getTime());

	}

	/**
	 * This constructor is used to construct Feeds which are already in the KB and are NOT going to be saved in the Database and sended
	 * @param comment
	 * @param sender
	 * @param datetime
     * @param reffeed
     */
	public ImplComment(String comment, Contact sender, Timestamp datetime, Feed reffeed){
		this.comment = comment;
		this.sender = sender;
		this.datetime = datetime;
		this.reffeed = reffeed;
	}

	@Override
	public Contact getSender() {
		return sender;
	}

	@Override
	public Timestamp getTimestamp() {
		return datetime;
	}

	@Override
	public Feed getRefFeed() {
		return reffeed;
	}

	@Override
	public String getContent() {
		return comment;
	}

	@Override
	public void delete() {
		reffeed.getComments(0).remove(this);
		//ToDo: Shark - delete Comment from Database
		//Implementation of DummyDB
		DummyDB.getInstance().removeComment(this, reffeed);
	}

	@Override
	public void save(){
		//ToDo: Shark - Safe Comment in KB and send it
		//Implementation of DummyDB
		DummyDB.getInstance().addComment(this, reffeed);
	}
}
