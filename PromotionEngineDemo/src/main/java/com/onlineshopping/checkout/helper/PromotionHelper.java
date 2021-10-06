package com.onlineshopping.checkout.helper;

import java.util.List;

import com.onlineshopping.model.Promotion;
import com.onlineshopping.model.PromotionType;
import com.onlineshopping.promotion.api.DiscountProcessor;
import com.onlineshopping.promotion.impl.BunchItemDiscountProcessor;

public class PromotionHelper {
	List<Promotion> promotions;
	
	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public String applyPromotion(OrderExp orderExp) {
		for(Promotion promotion: promotions) {
			if(promotion.isActive()) {
				DiscountProcessor dp = null;
				if(promotion.getPromotionType() == PromotionType.BUNCH_ITEM_DISCOUNT || promotion.getPromotionType() == PromotionType.COMBINED_ITEM_DISCOUNT) {
					dp = new BunchItemDiscountProcessor();
					orderExp.setExp(dp.discount(orderExp.getExp(), promotion.getDiscountCondition(), promotion.getDiscount()+""));
				}
			}
		}
		return orderExp.getExp();
	}

}
