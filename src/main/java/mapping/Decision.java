package mapping;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "Decyzja")
public class Decision {
	@Id
	@GeneratedValue
	@Column(name = "Id_decyzji")
	private int idDecision;
	@Column(name = "Czy_pozytywna")
	private char ifPositive;

	@OneToOne(mappedBy = "decision", cascade = CascadeType.ALL)
	private DetailedRaport detailedRaport;

	public Decision() {
		super();
	}

	public Decision(char ifPositive) {
		super();
		this.ifPositive = ifPositive;
	}

	public char getIfPositive() {
		return ifPositive;
	}

	public void setIfPositive(char ifPositive) {
		this.ifPositive = ifPositive;
	}

	public int getIdDecision() {
		return idDecision;
	}

	public void setIdDecision(int idDecision) {
		this.idDecision = idDecision;
	}

	public DetailedRaport getDetailedRaport() {
		return detailedRaport;
	}

	public void setDetailedRaport(DetailedRaport detailedRaport) {
		this.detailedRaport = detailedRaport;
	}

}
