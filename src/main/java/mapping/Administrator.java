package mapping;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Administrator")
public class Administrator {
	@Id
	@GeneratedValue
	@Column(name = "Id_administratora")
	private int idAdministratora;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Data_zatrudnienia")
	private Date dataZatrudnienia;


	@OneToOne
	private AdministratorSzczegoly administratorSzczegoly;

	public int getIdAdministratora() {
		return idAdministratora;
	}

	public void setIdAdministratora(int idAdministratora) {
		this.idAdministratora = idAdministratora;
	}

	public Date getDataZatrudnienia() {
		return dataZatrudnienia;
	}

	public void setDataZatrudnienia(Date dataZatrudnienia) {
		this.dataZatrudnienia = dataZatrudnienia;
	}

	public AdministratorSzczegoly getAdministratorSzczegoly() {
		return administratorSzczegoly;
	}

	public void setAdministratorSzczegoly(AdministratorSzczegoly administratorSzczegoly) {
		this.administratorSzczegoly = administratorSzczegoly;
	}

}
