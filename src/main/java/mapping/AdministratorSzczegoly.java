package mapping;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Administrator_szczegoly")
public class AdministratorSzczegoly {

	@Column(name = "Imie")
	private String imie;
	@Column(name = "Nazwisko")
	private String nazwisko;
	@Column(name = "Ulica")
	private String ulica;
	@Column(name = "Miasto")
	private String miasto;
	@Column(name = "Kod_pocztowy")
	private String kodPocztowy;
	@Column(name = "Telefon")
	private String telefon;
	@Column(name = "Data_urodzenia")
	@Temporal(TemporalType.DATE)
	private Date dataUrodzenia;
	@Column(name = "PESEL")
	private String pesel;

	public AdministratorSzczegoly(String imie, String nazwisko, String ulica, String miasto, String kodPocztowy,
			String telefon, Date dataUrodzenia, String pesel) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.ulica = ulica;
		this.miasto = miasto;
		this.kodPocztowy = kodPocztowy;
		this.telefon = telefon;
		this.dataUrodzenia = dataUrodzenia;
		this.pesel = pesel;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getMiasto() {
		return miasto;
	}

	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}

	public String getKodPocztowy() {
		return kodPocztowy;
	}

	public void setKodPocztowy(String kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Date getDataUrodzenia() {
		return dataUrodzenia;
	}

	public void setDataUrodzenia(Date dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

}
