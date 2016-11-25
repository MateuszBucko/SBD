package mapping;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Serwis")
public class Service {
	@Id
	@GeneratedValue
	private int seriveId;
	@Column(name = "Nazwa")
	private String name;
	@Column(name = "Adres")
	private String address;
	@Column(name = "Miasto")
	private String city;
	@Column(name = "Kod_pocztowy")
	private String postCode;
	@Column(name = "Telefon")
	private String phone;

	@OneToMany(mappedBy = "service")
	private List<Repair_Service> repairService;

	public Service(String name, String address, String city, String postCode, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.postCode = postCode;
		this.phone = phone;
	}

	public Service() {
		super();
	}

	public int getSeriveId() {
		return seriveId;
	}

	public void setSeriveId(int seriveId) {
		this.seriveId = seriveId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Repair_Service> getRepairService() {
		return repairService;
	}

	public void setRepairService(List<Repair_Service> repairService) {
		this.repairService = repairService;
	}

}
