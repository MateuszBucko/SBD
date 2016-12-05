package mapping;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Sklep")
public class Shop {
	@Id
	@GeneratedValue
	private int shopId;
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
	@Column(name = "NIP")
	private String nip;

	@OneToMany(mappedBy = "shop")
	private List<ReportedProduct> reportedProducts;

	public Shop(String name, String address, String city, String postCode, String phone, String nip) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.postCode = postCode;
		this.phone = phone;
		this.nip = nip;
	}

	public Shop() {
		super();
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
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

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public List<ReportedProduct> getReportedProducts() {
		return reportedProducts;
	}

	public void setReportedProducts(List<ReportedProduct> reportedProducts) {
		this.reportedProducts = reportedProducts;
	}

	@Override
	public String toString() {
		return "ID: " + shopId + "  Nazwa: " + name;
	}
	
	
	
	

}
