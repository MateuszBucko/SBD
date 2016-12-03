package mapping;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "Administrator_szczegoly")
public class AdministratorDetails {

	@Id
	@Column(name = "Id_administratora", unique = true, nullable = false)
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "administrator"))
	private int idAdministratora;
	@Column(name = "Imie")
	private String firstName;
	@Column(name = "Nazwisko")
	private String lastName;
	@Column(name = "Ulica")
	private String street;
	@Column(name = "Miasto")
	private String city;
	@Column(name = "Kod_pocztowy")
	private String postCode;
	@Column(name = "Telefon")
	private String phone;
	@Column(name = "Data_urodzenia")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	@Column(name = "PESEL")
	private String pesel;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Administrator administrator;

	public AdministratorDetails() {
		super();
	}

	public AdministratorDetails(String firstName, String lastName, String street, String city, String postCode,
			String phone, Date birthDate, String pesel) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.postCode = postCode;
		this.phone = phone;
		this.birthDate = birthDate;
		this.pesel = pesel;
	}

	public int getIdAdministratora() {
		return idAdministratora;
	}

	public void setIdAdministratora(int idAdministratora) {
		this.idAdministratora = idAdministratora;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	@Override
	public String toString() {
		return "id: "+idAdministratora + " imię: " + firstName + " nazwisko: " + lastName;
	}

	
}
