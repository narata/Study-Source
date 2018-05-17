package main.java.com.ia.visualization.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AttackerWhoisInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attacker_whois_info", catalog = "threat_feature")
public class AttackerWhoisInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sourceId;
	private String sourceIp;
	private String asn;
	private String ipNet;
	private String country;
	private String registerDate;
	private String asnRegistry;
	private String ipInterval;
	private String organization;
	private String address;
	private String email;
	private String name;
	private String phone;
	private String links;

	// Constructors

	/** default constructor */
	public AttackerWhoisInfo() {
	}

	/** full constructor */
	public AttackerWhoisInfo(Integer sourceId, String sourceIp, String asn,
			String ipNet, String country, String registerDate,
			String asnRegistry, String ipInterval, String organization,
			String address, String email, String name, String phone,
			String links) {
		this.sourceId = sourceId;
		this.sourceIp = sourceIp;
		this.asn = asn;
		this.ipNet = ipNet;
		this.country = country;
		this.registerDate = registerDate;
		this.asnRegistry = asnRegistry;
		this.ipInterval = ipInterval;
		this.organization = organization;
		this.address = address;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.links = links;
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

	@Column(name = "asn", length = 20)
	public String getAsn() {
		return this.asn;
	}

	public void setAsn(String asn) {
		this.asn = asn;
	}

	@Column(name = "ip_net", length = 30)
	public String getIpNet() {
		return this.ipNet;
	}

	public void setIpNet(String ipNet) {
		this.ipNet = ipNet;
	}

	@Column(name = "country", length = 20)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "register_date", length = 20)
	public String getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "asn_registry", length = 20)
	public String getAsnRegistry() {
		return this.asnRegistry;
	}

	public void setAsnRegistry(String asnRegistry) {
		this.asnRegistry = asnRegistry;
	}

	@Column(name = "ip_interval", length = 100)
	public String getIpInterval() {
		return this.ipInterval;
	}

	public void setIpInterval(String ipInterval) {
		this.ipInterval = ipInterval;
	}

	@Column(name = "organization", length = 100)
	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone", length = 30)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "links", length = 100)
	public String getLinks() {
		return this.links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

}