package entity;

import java.time.LocalDate;

import javax.persistence.Id;

import org.hibernate.annotations.Entity;

@Entity
public class ProductEntity {

	@Id
	private Integer	id;
	private String description;
	private float weight;
	private double price;
	private LocalDate manufacturingDate;
	private LocalDate expiryDate;
	
	public ProductEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductEntity(Integer id, String description, float weight, double price, LocalDate manufacturingDate,
			LocalDate expiryDate) {
		super();
		this.id = id;
		this.description = description;
		this.weight = weight;
		this.price = price;
		this.manufacturingDate = manufacturingDate;
		this.expiryDate = expiryDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(LocalDate manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", description=" + description + ", weight=" + weight + ", price=" + price
				+ ", manufacturingDate=" + manufacturingDate + ", expiryDate=" + expiryDate + "]";
	}

}
