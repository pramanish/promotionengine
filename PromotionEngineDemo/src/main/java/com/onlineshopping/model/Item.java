package com.onlineshopping.model;

import java.util.HashSet;
import java.util.Set;

public class Item {
	private String itemId;
	private String name;
	private String price;
	//@ManyToMany(cascade = { CascadeType.none })
    // @JoinTable(
        //name = "Item_Promotion", 
       // joinColumns = { @JoinColumn(name = "itemId") }, 
       // inverseJoinColumns = { @JoinColumn(name = "promotionId") }
    //)
    Set<Promotion> promotions = new HashSet<>();
    
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Set<Promotion> getPromotions() {
		return promotions;
	}
	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}
	
}
