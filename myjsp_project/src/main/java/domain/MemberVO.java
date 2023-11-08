package domain;

public class MemberVO {

	/*
	CREATE TABLE `member` (
			  `id` varchar(100) NOT NULL,
			  `pwd` varchar(100) NOT NULL,
			  `email` varchar(100) NOT NULL,
			  `age` int DEFAULT '0',
			  `regdate` datetime DEFAULT CURRENT_TIMESTAMP,
			  `lastlogin` datetime DEFAULT CURRENT_TIMESTAMP,
			  PRIMARY KEY (`id`)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
	*/


	private String id;
	private String pwd;
	private String email;
	private int age;
	private String regdate;
	private String lastlogin;
	
	
	public MemberVO() {
	}
	
	public MemberVO(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	
	public MemberVO(String id, String pwd, String email, int age) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.age = age;
	}

	public MemberVO(String id, String pwd, String email, int age, String regdate, String lastlogin) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.age = age;
		this.regdate = regdate;
		this.lastlogin = lastlogin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	@Override
	public String toString() {
		return "MemberVO ["    +     "id=" + id + ", pwd=" + pwd + ", email=" + email + ", age=" + age
				+ ", regdate=" + regdate + ", lastlogin=" + lastlogin + "]";
	}
	
	
	
}
