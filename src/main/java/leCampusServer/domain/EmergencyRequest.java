package leCampusServer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmergencyRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int serverId;
	private String userName;
	private String time;
	private String latitude;
	private String longitude;
	private String type; // Medical or Security
	
	public EmergencyRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmergencyRequest(String userName, String time, String latitude, String longitude, String type) {
		super();
		this.userName = userName;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "EmergencyRequest [userName=" + userName + ", time=" + time + ", latitude=" + latitude + ", longitude="
				+ longitude + ", type=" + type + "]";
	}

}
