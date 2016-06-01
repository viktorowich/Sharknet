package berlin.htw.schneider.viktor.sharknet.api;

import java.util.List;

/**
 * Created by timol on 12.05.2016.
 *
 * Interface represents a Contact (a Person) in SharkNet
 */
public interface Contact {



	/**
	 * returns the Nickname of the contact
	 * @return
     */
    public String getNickname();
	public void setNickname(String nickname);


	/**
	 * returns the UID of the contact
	 * @return
     */
    public String getUID();
	public void setUID(String uid);

	/**
	 * Returns the profilepicture of a contact
	 * @return
     */
	//ToDo: Implement - File - Mime Type integrieren
    public String getPicture();
	public void setPicture(String pic);

	/**
	 * returns the PublicKey of the contact
	 * @return
     */
    public String getPublicKey();
	public void setPublicKey(String publicKey);

	/**
	 * Deletes the Contact from the Database
	 */
	public void delete();

	/**
	 * updates a Contact in the Database
	 */
	public void update();

	/**
	 * Safes the Contact in the KB
	 */
	public void save();

	/**
	 * returns a List of all Interests the profile is interested in
	 * @return
	 */
	public List<Interest> getInterests();


}
