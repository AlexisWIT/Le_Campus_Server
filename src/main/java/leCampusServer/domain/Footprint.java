package leCampusServer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Footprint {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serverId;
	private Integer localId;
	
	private String title;
    private String description;
    private String nodeList;        // String of JSONArray [ArrayList<HashMap<String, Object>>]
    private String createTime;

    private String creator;       // Author username
    private String length;        // in metre
    private String type;			// walk, run, recycle, drive
    private Long totalTime;         // in millisec - convert to min
    private Integer privacy = 0;
	
	public Footprint() {
		// TODO Auto-generated constructor stub
	}

	public Footprint(Integer localId, String title, String description, String nodeList, String createTime,
			String creator, String length, String type, Long totalTime, Integer privacy) {
		super();
		this.localId = localId;
		this.title = title;
		this.description = description;
		this.nodeList = nodeList;
		this.createTime = createTime;
		this.creator = creator;
		this.length = length;
		this.type = type;
		this.totalTime = totalTime;
		this.privacy = privacy;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNodeList() {
		return nodeList;
	}

	public void setNodeList(String nodeList) {
		this.nodeList = nodeList;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Integer privacy) {
		this.privacy = privacy;
	}

	@Override
	public String toString() {
		return "Footprint [serverId=" + serverId + ", localId=" + localId + ", title=" + title + ", description="
				+ description + ", nodeList=" + nodeList + ", createTime=" + createTime + ", creator=" + creator
				+ ", length=" + length + ", type=" + type + ", totalTime=" + totalTime + ", privacy=" + privacy + "]";
	}
	
	

}
