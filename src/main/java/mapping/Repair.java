package mapping;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Naprawa")
public class Repair {
	@Id
	@GeneratedValue
	@Column(name = "Id_naprawy")
	private int repairId;

	@OneToOne
	@JoinColumn(name = "Id_reklamacji")
	private Complaint complaint;

	@Column(name = "Data_zgloszenia")
	@Temporal(TemporalType.DATE)
	private Date notificationDate;

	@OneToMany(mappedBy = "repair")
	private List<Repair_Service> repairService;

	public Repair() {
		super();
	}

	public Repair(Date notificationDate) {
		super();
		this.notificationDate = notificationDate;
	}

	public int getRepairId() {
		return repairId;
	}

	public void setRepairId(int repairId) {
		this.repairId = repairId;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public List<Repair_Service> getRepairService() {
		return repairService;
	}

	public void setRepairService(List<Repair_Service> repairService) {
		this.repairService = repairService;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

}
