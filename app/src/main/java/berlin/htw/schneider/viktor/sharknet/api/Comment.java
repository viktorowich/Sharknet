package berlin.htw.schneider.viktor.sharknet.api;

import java.sql.Timestamp;

/**
 * Created by timol on 12.05.2016.
 *
 * Interface represents the comments belonging to feeds
 */
public interface Comment {


	/**
	 * Returns the author of a comment
	 * @return
     */
    public String getSender();

	/**
	 * Returns the Date and Time a comment was created
	 * @return
     */
    public Timestamp getTimestamp();

	/**
	 * Returns the Feed the comment is referencing
	 * @return
     */
    public Feed getRefFeed();
	/**
	 * returns the content of a comment
	 * @return
	 */
	//ToDo: Implement - File - Mime Type integrieren
    public String getContent();

	/**
	 * Deletes Comment from DB
	 */
	public void deleteComment();

	/**
	 * safes the comment in the DB and sends it
	 */
	public void safeInKB();
}

