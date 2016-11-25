package mapping;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Serwis_naprawa")
public class Repair_Service {
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "Opis")
	private String description;
	@Column(name = "Data_naprawy")
	@Temporal(TemporalType.DATE)
	private Date repairDate;

	@ManyToOne
	@JoinColumn(name = "Id_naprawy")
	private Repair repair;
	@ManyToOne
	@JoinColumn(name = "Id_serwisu")
	private Service service;

	public Repair_Service() {
		super();
	}

	public Repair_Service(String description, Date repairDate) {
		super();
		this.description = description;
		this.repairDate = repairDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}
