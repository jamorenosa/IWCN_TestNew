package com.iwcn.master.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String name;
	float price;
	String description;
	String size;
	String origin;
	int yearLot;
	int monthLot;
	int dayLot;
	public Product() {}
	public Product(String name, float price, String description, String size, String origin, 
			int yearLot, int monthLot, int dayLot) {
		this.name=name;
		this.price=price;
		this.description=description;
		this.size=size;
		this.origin=origin;
		this.yearLot=yearLot;
		this.monthLot=monthLot;
		this.dayLot=dayLot;
	}
	public int getYearLot() { return yearLot; }
	public void setYearLot(int yearLot) { this.yearLot = yearLot; }
	public int getMonthLot() { return monthLot; }
	public void setMonthLot(int monthLot) { this.monthLot = monthLot; }
	public int getDayLot() { return dayLot; }
	public void setDayLot(int dayLot) { this.dayLot = dayLot; }
	public void setId(long id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setPrice(float price) { this.price = price; }
	public void setDescription(String description) { this.description = description; }
	public void setSize(String size) { this.size = size; }
	public void setOrigin(String origin) { this.origin = origin; }
	public String getName() {return this.name;}
	public float getPrice() {return this.price;}
	public String getDescription() {return this.description;}
	public String getSize() {return this.size;}
	public String getOrigin() {return this.origin;}
	public String writeLot() {
		return ""+((Integer)this.dayLot).toString()+"-"+((Integer)this.monthLot).toString()+"-"+((Integer)this.yearLot).toString();
		}
	public Long getId() {return this.id;}
}
