package berlin.htw.schneider.viktor.sharknet.api;

import java.util.LinkedList;
import java.util.List;



/**
 * Created by timol on 16.05.2016.
 */
public class ImplProfile implements Profile {

	//ToDo: Implement - Profil hat eigene Kontakte
	//TODo: Seperate Datasets for Profiles


	Contact c;
	String password ="";

	public ImplProfile(Contact c){
		this.c = c;
	}

	@Override
	public Contact getContact() {
		return c;
	}

	@Override
	public void setContact(Contact c) {
		this.c = c;
	}

	@Override
	public Setting getSettings() {
		return null;
	}

	@Override
	public void setSettings() {

	}

	@Override
	public void delete() {

		//ToDo: Shark - delete the Profile in the KB
		//Implementation of DummyDB
		DummyDB.getInstance().removeProfile(this);
	}

	@Override
	public void save() {

		//ToDo: Shark - saveProfile in the KB
		//Implementation of DummyDB
		DummyDB.getInstance().addProfile(this);
	}

	@Override
	public void update() {
		//ToDo: Shark - update Profile in the KB
	}

	@Override
	public boolean login(String password) {
		if(this.password.equals(password)){
			return true;
		}
		else return false;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	//ToDo: Implement - Interest
	//ToDo: Implement - Settings
	//ToDo: Implement - Generate KeyPairs
	//ToDo: Clearify - How Notifications for the GUI Work (Action Listener)

}
