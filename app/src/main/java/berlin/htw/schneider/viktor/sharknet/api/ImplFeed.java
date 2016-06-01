package berlin.htw.schneider.viktor.sharknet.api;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by timol on 16.05.2016.
 */
public class ImplFeed implements Feed {

	//Todo: Content class mit getFiletype (aus Shark), Inputstream weiter reichen


	String content;

	List<Comment> comment_list = new LinkedList<>();
	Timestamp datetime;
	Interest interest;
	Contact sender;

	//ToDo: Clearify - safe Method has always to be colled explicit


	/**
	 * This constructor is used to construct new Feeds which are going to be safed in the Database and sended
	 * @param content
	 * @param interest
	 * @param sender
     */
	public ImplFeed(String content, Interest interest, Contact sender){
		this.content = content;
		this.interest = interest;
		this.sender = sender;
		datetime = new Timestamp(new Date().getTime());
	}

	/**
	 * This Constructor is used to construct the Feeds already in the Database, it will not send or safe them
	 * @param content
	 * @param interest
	 * @param sender
     * @param datetime
     */
	public ImplFeed(String content, Interest interest, Contact sender, Timestamp datetime){
		this.sender = sender;
		this.interest = interest;
		this.content = content;
		this.datetime = datetime;
	}

	@Override
	public Interest getInterest() {
		return interest;
	}

	@Override
	public Timestamp getTimestamp() {
		return datetime;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public Contact getSender() {
		return sender;
	}

	@Override
	public List<Comment> getComments(int count) {
		//ToDo: Shark - search for comments construct the objects and fill the list
		return comment_list;
	}

	@Override
	public void newComment(String comment, Contact author) {
		Comment c = new ImplComment(comment, author, this);
		comment_list.add(c);
	}

	@Override
	public void save() {
		//ToDo: Shark - safe Feed in KB and sends it
		//Implementation of DummyDB
		DummyDB.getInstance().addfeed(this);
	}

	@Override
	public void delete() {
		//ToDo: Shark - delte Feed in KB
		//Implementation of DummyDB
		DummyDB.getInstance().removefeed(this);
	}
}

