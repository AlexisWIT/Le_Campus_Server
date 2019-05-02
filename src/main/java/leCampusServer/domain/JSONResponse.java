package leCampusServer.domain;

import java.util.ArrayList;
import java.util.List;

public class JSONResponse {
	String result, message;
	List<BuildingGeoFence> buildingGeoFenceList = new ArrayList<>();
	List<CrimeGeoFence> crimeGeoFenceList = new ArrayList<>();
	List<Footprint> footprintList = new ArrayList<>();
	List<PublicEvent> publicEventList = new ArrayList<>();
	List<User> userList = new ArrayList<>();
	List<EmergencyRequest> emergencyRequestList = new ArrayList<>();
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<BuildingGeoFence> getBuildingGeoFenceList() {
		return buildingGeoFenceList;
	}
	public void setBuildingGeoFenceList(List<BuildingGeoFence> buildingGeoFenceList) {
		this.buildingGeoFenceList = buildingGeoFenceList;
	}
	public List<CrimeGeoFence> getCrimeGeoFenceList() {
		return crimeGeoFenceList;
	}
	public void setCrimeGeoFenceList(List<CrimeGeoFence> crimeGeoFenceList) {
		this.crimeGeoFenceList = crimeGeoFenceList;
	}
	public List<Footprint> getFootprintList() {
		return footprintList;
	}
	public void setFootprintList(List<Footprint> footprintList) {
		this.footprintList = footprintList;
	}
	public List<PublicEvent> getPublicEventList() {
		return publicEventList;
	}
	public void setPublicEventList(List<PublicEvent> publicEventList) {
		this.publicEventList = publicEventList;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public List<EmergencyRequest> getEmergencyRequestList() {
		return emergencyRequestList;
	}
	public void setEmergencyRequestList(List<EmergencyRequest> emergencyRequestList) {
		this.emergencyRequestList = emergencyRequestList;
	}

}
