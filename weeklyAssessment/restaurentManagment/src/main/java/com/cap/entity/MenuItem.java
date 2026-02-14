package com.cap.entity;
import jakarta.persistence.*;

@Entity
@Table(name="menu_item")
public class MenuItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name,category;
	private double price;
	private boolean available;
	
	public MenuItem() {}
	
	public MenuItem(String name,double price,String category,boolean available) {
		this.setName(name);
		this.setPrice(price);
		this.setCategory(category);
		this.setAvailable(available);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
	
}
