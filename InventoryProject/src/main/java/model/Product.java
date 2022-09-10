package model;

import java.time.LocalDate;
import java.util.Date;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;

public class Product {

	private Integer	id;
	private String description;
	private Float weight;
	private Double price;
	private LocalDate manufacturingDate;
	private LocalDate expiryDate;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Integer id, String description, Float weight, Double price, LocalDate manufacturingDate,
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

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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
		return "ProductModel [id=" + id + ", description=" + description + ", weight=" + weight + ", price=" + price
				+ ", manufacturingDate=" + manufacturingDate + ", expiryDate=" + expiryDate + "]";
	}

	

}

