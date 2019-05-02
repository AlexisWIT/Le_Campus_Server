package leCampusServer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PublicEvent {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serverId;	// ID in server database
	private Integer localId;	// Id in client device database
	
	private String holdBy;
	private String eventType;
	private String eventTitle;  // 'Cryptography and Internet Security' or 'Neon Night: Riders vs Rocks'
	private String eventDesc;
	private String eventCode;

	private String location;    // 'Attenborough' or 'The Belmont Hotel'
	private String address;     // 'ATT LT3' or '20 De Montfort Square'
	private String postCode;
	private String city;        // Leicester or London
	private String region;      // 'England', 'East Midland' or 'Leicestershire'
	private String country;     // 'UK' or 'US'

	private String lat;         // Latitude
	private String lon;         // Longitude

	private String startTime;
	private String endTime;
	private String duration;
	private String weekDay;			// '1' Monday, '2' Tuesday ..
	private Integer isAllDay = 0;   // '1' all-day event. '0' not
	private Integer isDisabled = 0; // '1' disabled (completed or cancelled). '0' not

	private String host;			// Lecturer or Organizer in name
	private String email;

	private String detailUrl;
	private String imageUrl;
	private String offers;
    
	public PublicEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PublicEvent(Integer localId, String holdBy, String eventType, String eventTitle, String eventDesc,
			String eventCode, String location, String address, String postCode, String city, String region,
			String country, String lat, String lon, String startTime, String endTime, String duration, String weekDay,
			Integer isAllDay, Integer isDisabled, String host, String email, String detailUrl, String imageUrl,
			String offers) {
		super();
		this.localId = localId;
		this.holdBy = holdBy;
		this.eventType = eventType;
		this.eventTitle = eventTitle;
		this.eventDesc = eventDesc;
		this.eventCode = eventCode;
		this.location = location;
		this.address = address;
		this.postCode = postCode;
		this.city = city;
		this.region = region;
		this.country = country;
		this.lat = lat;
		this.lon = lon;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.weekDay = weekDay;
		this.isAllDay = isAllDay;
		this.isDisabled = isDisabled;
		this.host = host;
		this.email = email;
		this.detailUrl = detailUrl;
		this.imageUrl = imageUrl;
		this.offers = offers;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public Integer getLocalId() {
		return localId;
	}

	public void setLocalId(Integer localId) {
		this.localId = localId;
	}

	public String getHoldBy() {
		return holdBy;
	}

	public void setHoldBy(String holdBy) {
		this.holdBy = holdBy;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public Integer getIsAllDay() {
		return isAllDay;
	}

	public void setIsAllDay(Integer isAllDay) {
		this.isAllDay = isAllDay;
	}

	public Integer getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getOffers() {
		return offers;
	}

	public void setOffers(String offers) {
		this.offers = offers;
	} 

}
