package mapping;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

@Entity(name = "Administrator")
public class Administrator {
	@Id
	@GeneratedValue
	@Column(name = "Id_administratora")
	private int idAdministrator;
	@Temporal(TemporalType.DATE)
	@Column(name = "Data_zatrudnienia")
	private Date hireDate;

	@OneToOne(mappedBy = "administrator", cascade=CascadeType.ALL)
	private AdministratorDetails administratorDetails;

	@OneToMany(mappedBy = "administrator")
	private List<Complaint> complaints;

	public Administrator() {
		super();
	}

	public Administrator(Date hireDate) {
		super();
		this.hireDate = hireDate;
	}

	public int getIdAdministrator() {
		return idAdministrator;
	}

	public void setIdAdministrator(int idAdministrator) {
		this.idAdministrator = idAdministrator;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public AdministratorDetails getAdministratorDetails() {
		return administratorDetails;
	}

	public void setAdministratorDetails(AdministratorDetails administratorDetails) {
		this.administratorDetails = administratorDetails;
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

}
