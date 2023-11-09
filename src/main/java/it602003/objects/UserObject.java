package it602003.objects;

public class UserObject {
	private int user_id;
	private String user_fullname;
	public int getUser_id() {
		return user_id;
	}
	public String getUser_fullname() {
		return user_fullname;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
	}
	@Override
	public String toString() {
		return "UserObject [user_id=" + user_id + ", user_fullname=" + user_fullname + "]";
	}
	
}
