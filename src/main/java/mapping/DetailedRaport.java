package mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name="Raport_szczegolowy")
public class DetailedRaport {
	@Id
	@Column(name = "Id_decyzji", unique = true, nullable = false)
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "decision"))
	private int idDecision;
	@Column(name = "Opis_szczegolowy")
	private String description;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Decision decision;

	public DetailedRaport() {
		super();
	}

	public DetailedRaport(String description) {
		super();
		this.description = description;
	}

	public int getIdDecision() {
		return idDecision;
	}

	public void setIdDecision(int idDecision) {
		this.idDecision = idDecision;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}

}
