package it602003.process;

import java.util.ArrayList;

import it602003.objects.UserObject;

public interface User {
	public ArrayList<UserObject> getUserObjects(UserObject similar, byte total);
	public UserObject getUserObject(int id);
}
