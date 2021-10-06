package com.onlineshopping.model;

public class Promotion {
	private long promotionId;
	private String promotionName;
	private boolean isActive;
	private PromotionType promotionType;
	private String discountCondition;
	private double discount;
	
	public long getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(long promotionId) {
		this.promotionId = promotionId;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public PromotionType getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(PromotionType promotionType) {
		this.promotionType = promotionType;
	}
	public String getDiscountCondition() {
		return discountCondition;
	}
	public void setDiscountCondition(String discountCondition) {
		this.discountCondition = discountCondition;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}
