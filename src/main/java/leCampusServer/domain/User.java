package leCampusServer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serverId;
    private Integer localId;
    private String password;

    private String studentNumber;
    private String nickname;
    private String ucasNumber;// Not used
    private String realname;
    private String preferedName;// Not used
    private String dateofbirth;// Not used
    private String uolEmail;
    private String friendList; // JSONArray: [{"serverId", 15},{"serverId", 17}]
	
    public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public User(String studentNumber, String realname, String uolEmail) {
		super();
		this.studentNumber = studentNumber;
		this.realname = realname;
		this.uolEmail = uolEmail;
	}

	public User(Integer localId, String password, String studentNumber, String nickname, String ucasNumber,
			String realname, String preferedName, String dateofbirth, String uolEmail, String friendList) {
		super();
		this.localId = localId;
		this.password = password;
		this.studentNumber = studentNumber;
		this.nickname = nickname;
		this.ucasNumber = ucasNumber;
		this.realname = realname;
		this.preferedName = preferedName;
		this.dateofbirth = dateofbirth;
		this.uolEmail = uolEmail;
		this.friendList = friendList;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public Integer getLocalId() {
		return localId;
	}

	public void setLocalId(Integer localId) {
		this.localId = localId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUcasNumber() {
		return ucasNumber;
	}

	public void setUcasNumber(String ucasNumber) {
		this.ucasNumber = ucasNumber;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPreferedName() {
		return preferedName;
	}

	public void setPreferedName(String preferedName) {
		this.preferedName = preferedName;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getUolEmail() {
		return uolEmail;
	}

	public void setUolEmail(String uolEmail) {
		this.uolEmail = uolEmail;
	}

	public String getFriendList() {
		return friendList;
	}

	public void setFriendList(String friendList) {
		this.friendList = friendList;
	}
}
