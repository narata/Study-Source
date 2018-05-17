package main.java.com.ia.visualization.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AttackerGeoipInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attacker_geoip_info", catalog = "threat_feature")
public class AttackerGeoipInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sourceId;
	private String sourceIp;
	private String dstIp;
	private String protocol;
	private Integer port;
	private String times;
	private Integer counts;
	private String dns;
	private String country;
	private String province;
	private String city;
	private Float latitude;
	private Float longitude;
	private String sourceTable;

	// Constructors

	/** default constructor */
	public AttackerGeoipInfo() {
	}

	/** full constructor */
	public AttackerGeoipInfo(Integer sourceId, String sourceIp, String dstIp,
			String protocol, Integer port, String times, Integer counts,
			String dns, String country, String province, String city,
			Float latitude, Float longitude, String sourceTable) {
		this.sourceId = sourceId;
		this.sourceIp = sourceIp;
		this.dstIp = dstIp;
		this.protocol = protocol;
		this.port = port;
		this.times = times;
		this.counts = counts;
		this.dns = dns;
		this.country = country;
		this.province = province;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.sourceTable = sourceTable;
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

	@Column(name = "source_id")
	public Integer getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_ip", length = 20)
	public String getSourceIp() {
		return this.sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	@Column(name = "dst_ip", length = 20)
	public String getDstIp() {
		return this.dstIp;
	}

	public void setDstIp(String dstIp) {
		this.dstIp = dstIp;
	}

	@Column(name = "protocol", length = 20)
	public String getProtocol() {
		return this.protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	@Column(name = "port")
	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Column(name = "times", length = 20)
	public String getTimes() {
		return this.times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	@Column(name = "counts")
	public Integer getCounts() {
		return this.counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	@Column(name = "dns", length = 50)
	public String getDns() {
		return this.dns;
	}

	public void setDns(String dns) {
		this.dns = dns;
	}

	@Column(name = "country", length = 50)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "province", length = 50)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "latitude", precision = 12, scale = 0)
	public Float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude", precision = 12, scale = 0)
	public Float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@Column(name = "source_table", length = 50)
	public String getSourceTable() {
		return this.sourceTable;
	}

	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}

}