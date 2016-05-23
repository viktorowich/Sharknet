package berlin.htw.schneider.viktor.sharknet.api;

/**
 * Created by timol on 16.05.2016.
 */

//ToDo: Clearify - Groups as own Interface/ImplClass


public class ImplContact implements Contact {

	String nickname;
	String uid;
	String publickey;


	/**
	 * Constructor to add new Contact and Safe it to the Database
	 * @param nickname
	 * @param uid
	 * @param publickey
     */
	public ImplContact(String nickname, String uid, String publickey){
		this.nickname = nickname;
		this.uid = uid;
		this.publickey = publickey;
		//ToDo: Clearify - public key exchange
		safeInKB();
	}

	@Override
	public String getNickname() {
		return nickname;
	}

	@Override
	public String getUID() {
		return uid;
	}

	@Override
	public String getPicture() {

		//ToDo: Implement - Profilepictures
		return null;
	}

	@Override
	public String getPublicKey() {
		return "-----BEGIN PUBLIC KEY-----\n" +
			"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqGKukO1De7zhZj6+H0qtjTkVxwTCpvKe4eCZ0\n" +
			"FPqri0cb2JZfXJ/DgYSF6vUpwmJG8wVQZKjeGcjDOL5UlsuusFncCzWBQ7RKNUSesmQRMSGkVb1/\n" +
			"3j+skZ6UtW+5u09lHNsj6tQ51s1SPrCBkedbNf0Tp0GbMJDyR4e9T04ZZwIDAQAB\n" +
			"-----END PUBLIC KEY-----";

		//ToDo: Implement -  Key-Mgmt
	}

	@Override
	public void deleteContact() {
		//ToDo: Shark - Delete Contact from the Database
	}

	@Override
	public void updateContact(String nickname, String uid, String publicKey) {
		this.nickname = nickname;
		this.uid = uid;
		this.publickey = publicKey;
		//ToDo: Shark -  Update of the Contact in the Database
	}

	@Override
	public void safeInKB(){
		//ToDo: Shark - Safe Contact in KB

	}
}
