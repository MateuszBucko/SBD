package mapping;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Reklamacja")
public class Complaint {
	@Id
	@GeneratedValue
	@Column(name = "Id_reklamacji")
	private int complaintId;
	@Column(name = "Opis_reklamacji")
	private String description;
	@Column(name = "Data_zlozenia")
	@Temporal(TemporalType.DATE)
	private Date complaintDate;

	@ManyToOne
	@JoinColumn(name = "Id_produktu")
	private ReportedProduct reportedProduct;

	@ManyToOne
	@JoinColumn(name = "Id_administratora")
	private Administrator administrator;

	@OneToOne
	@JoinColumn(name = "Id_decyzji")
	private Decision decision;

	public Complaint(String description, Date complaintDate) {
		super();
		this.description = description;
		this.complaintDate = complaintDate;
	}

	public Complaint() {
		super();
	}

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}

	public ReportedProduct getReportedProduct() {
		return reportedProduct;
	}

	public void setReportedProduct(ReportedProduct reportedProduct) {
		this.reportedProduct = reportedProduct;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}

	@Override
	public String toString() {
		return "ID:" + complaintId + ", Produkt: " + reportedProduct;
	}
	
	

}
