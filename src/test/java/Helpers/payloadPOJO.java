package Helpers;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.juneau.annotation.Beanc;

public class payloadPOJO implements Serializable{


	private String Name;
	private int cost;
	private String color;
	private String[] vendors;
	

	

	public payloadPOJO(String name, int cost, String color, String[] vendors) {
		this.Name = name;
		this.cost = cost;
		this.color = color;
		this.vendors = vendors;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String[] getVendors() {
		return vendors;
	}
	public void setVendors(String[] vendors) {
		this.vendors = vendors;
	}
	
	@Override
	public String toString() {
		return "payloadPOJO [Name=" + Name + ", cost=" + cost + ", color=" + color + ", vendors="
				+ Arrays.toString(vendors) + "]";
	}
	
	
}
