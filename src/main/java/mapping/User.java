package mapping;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="Uzytkownik")
public class User {
	@Id
	@GeneratedValue
	@Column(name="Id_uzytkownika")
	private int userId;
	@Column(name="Imie")
	private String firstName;
	@Column(name="Nazwisko")
	private String lastName;
	@Column(name="Adres")
	private String Address;
	@Column(name="Miasto")
	private String city;
	@Column(name="Email")
	private String email;
	@Column(name="Kod_pocztowy")
	private String postCode;
	@Column(name="Telefon")
	private String phone;
	
	@OneToMany(mappedBy="user")
	private List<ReportedProduct> reportedProducts;

	public User(String firstName, String lastName, String address, String city, String email, String postCode,
			String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		Address = address;
		this.city = city;
		this.email = email;
		this.postCode = postCode;
		this.phone = phone;
	}

	public User() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<ReportedProduct> getReportedProducts() {
		return reportedProducts;
	}

	public void setReportedProducts(List<ReportedProduct> reportedProducts) {
		this.reportedProducts = reportedProducts;
	}

	
}
