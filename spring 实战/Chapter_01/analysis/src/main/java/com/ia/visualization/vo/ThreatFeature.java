package main.java.com.ia.visualization.vo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ThreatFeature entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "threat_feature", catalog = "threat_feature")
public class ThreatFeature implements java.io.Serializable {

	// Fields

	private Integer id;
	private String ip;
	private Float timeDiff;
	private Integer commNum;
	private Timestamp timeStart;
	private Integer threatType;
	private Integer threatLevel;

	// Constructors

	/** default constructor */
	public ThreatFeature() {
	}

	/** full constructor */
	public ThreatFeature(String ip, Float timeDiff, Integer commNum,
			Timestamp timeStart, Integer threatType, Integer threatLevel) {
		this.ip = ip;
		this.timeDiff = timeDiff;
		this.commNum = commNum;
		this.timeStart = timeStart;
		this.threatType = threatType;
		this.threatLevel = threatLevel;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ip", length = 30)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "time_diff", precision = 12, scale = 0)
	public Float getTimeDiff() {
		return this.timeDiff;
	}

	public void setTimeDiff(Float timeDiff) {
		this.timeDiff = timeDiff;
	}

	@Column(name = "comm_num")
	public Integer getCommNum() {
		return this.commNum;
	}

	public void setCommNum(Integer commNum) {
		this.commNum = commNum;
	}

	@Column(name = "time_start", length = 19)
	public Timestamp getTimeStart() {
		return this.timeStart;
	}

	public void setTimeStart(Timestamp timeStart) {
		this.timeStart = timeStart;
	}

	@Column(name = "threat_type")
	public Integer getThreatType() {
		return this.threatType;
	}

	public void setThreatType(Integer threatType) {
		this.threatType = threatType;
	}

	@Column(name = "threat_level")
	public Integer getThreatLevel() {
		return this.threatLevel;
	}

	public void setThreatLevel(Integer threatLevel) {
		this.threatLevel = threatLevel;
	}

}