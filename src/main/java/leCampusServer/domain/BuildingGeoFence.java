package leCampusServer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Data from: https://www.le.ac.uk/maps/assets/dist/json/data.min.js
@Entity
public class BuildingGeoFence {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serverId;
	private String building;
	private String address;
	private String imageURL;
	private String websiteURL;
	private String collegeName;
	private String directionPoint; // the coordinate in the middle
	private String nodeList; 
	
	/**
	 * GeoFence Data Sample
	 * "coordinates": [
	 * 	[
	 * 		[-1.1239045,52.6205893],
	 * 		[-1.1236241,52.6204607],
	 * 		[-1.1234425,52.6206088],
	 * 		[-1.1234224,52.6205351]
	 * 	]
	 * ]
	 * 
	 */

	public BuildingGeoFence() {
		// TODO Auto-generated constructor stub
	}

	public BuildingGeoFence(String building, String address, String imageURL, String websiteURL,
			String collegeName, String directionPoint, String nodeList) {
		super();
		this.building = building;
		this.address = address;
		this.imageURL = imageURL;
		this.websiteURL = websiteURL;
		this.collegeName = collegeName;
		this.directionPoint = directionPoint;
		this.nodeList = nodeList;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getWebsiteURL() {
		return websiteURL;
	}

	public void setWebsiteURL(String websiteURL) {
		this.websiteURL = websiteURL;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getDirectionPoint() {
		return directionPoint;
	}

	public void setDirectionPoint(String directionPoint) {
		this.directionPoint = directionPoint;
	}

	public String getNodeList() {
		return nodeList;
	}

	public void setNodeList(String nodeList) {
		this.nodeList = nodeList;
	}
	
}
