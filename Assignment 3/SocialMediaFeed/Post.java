
public abstract class Post {

	//user user
	
	
	//private string get date
	private String date;
	private String time;
	private User user;
	//creating getters
	public String getDate() {
		return this.date;
	}
	
	public String getTime() {
		return this.time;
	}
	public String getDateAndTime() {
		return this.date + " " + this.time;
		
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
