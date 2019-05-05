package leCampusServer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//	Data from: https://data.police.uk
//	52.633, -1.133 (+-0.02)
//	https://data.police.uk/api/crimes-street/all-crime?poly=52.643905,-1.130949:52.631823,-1.112374:52.615347,-1.130951:52.633000,-1.153609&date=2019-01

@Entity
public class CrimeGeoFence {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serverId;
	private String category;
	private String latitude;
	private String longitude;
	private String location; // e.g. On or near Moulton Road
	private String timeInMonth; // yyyy-MM e.g. 2017-01
	
	public CrimeGeoFence() {
		// TODO Auto-generated constructor stub
	}

	public CrimeGeoFence(String category, String latitude, String longitude, String location, String timeInMonth) {
		super();
		this.category = category;
		this.latitude = latitude;
		this.longitude = longitude;
		this.location = location;
		this.timeInMonth = timeInMonth;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTimeInMonth() {
		return timeInMonth;
	}

	public void setTimeInMonth(String timeInMonth) {
		this.timeInMonth = timeInMonth;
	}

}
