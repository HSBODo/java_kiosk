package vo;

public class User {
	public String id;
	public String pwd;
	public boolean manager;// true=관리자 , false=유저
	public User() {	}

	public User(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "[ID:" + id + "  " + "PW:" + pwd+"]";
	}
	
	public User(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}

	public boolean isManager() {
		return manager;
	}

	public User(String id, String pwd, boolean manager) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.manager = manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
