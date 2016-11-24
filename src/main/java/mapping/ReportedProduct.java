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

@Entity(name = "Produkt_zglaszany")
public class ReportedProduct {
	@Id
	@GeneratedValue
	@Column(name = "Id_produktu")
	private int productId;
	@Column(name = "Nazwa")
	private String name;
	@Column(name = "Model")
	private String model;
	@Column(name = "Producent")
	private String producer;
	@Temporal(TemporalType.DATE)
	@Column(name = "Data_zakupu")
	private Date buyDate;

	@ManyToOne
	@JoinColumn(name = "Id_sklepu")
	private Shop shop;
	@ManyToOne
	@JoinColumn(name = "Id_uzytkownika")
	private User user;

	public ReportedProduct() {
		super();
	}

	public ReportedProduct(String name, String model, String producer, Date buyDate) {
		super();
		this.name = name;
		this.model = model;
		this.producer = producer;
		this.buyDate = buyDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
