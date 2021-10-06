package com.onlineshopping.checkout;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;

import com.onlineshopping.checkout.helper.OrderExp;
import com.onlineshopping.checkout.helper.PriceEvalExpHelper;
import com.onlineshopping.checkout.helper.PromotionHelper;
import com.onlineshopping.model.Promotion;
import com.onlineshopping.model.PromotionType;
import com.onlineshopping.promotion.api.DiscountProcessor;
import com.onlineshopping.promotion.impl.BunchItemDiscountProcessor;



public class CheckoutProcessorFacade {
	
	PriceEvalExpHelper priceEvalExpHelper;
	
	PromotionHelper promotionHelper;
	
	public PriceEvalExpHelper getPriceEvalExpHelper() {
		return priceEvalExpHelper;
	}

	public void setPriceEvalExpHelper(PriceEvalExpHelper priceEvalExpHelper) {
		this.priceEvalExpHelper = priceEvalExpHelper;
	}

	public PromotionHelper getPromotionHelper() {
		return promotionHelper;
	}

	public void setPromotionHelper(PromotionHelper promotionHelper) {
		this.promotionHelper = promotionHelper;
	}
	
	public Double checkout(String order) throws ScriptException {
		List<String> orders= Arrays.asList(order.split("\\|"));
		OrderExp orderExp = priceEvalExpHelper.createOrderExpression(orders);
		orderExp.setExp(promotionHelper.applyPromotion(orderExp));
		orderExp.setExp(priceEvalExpHelper.addOriginalPriceInExp(orderExp.getExp()));
		String finalPrice = priceEvalExpHelper.evalPriceExp(orderExp);
		if(StringUtils.isBlank(finalPrice)) {
			return 0d;
		}
		return new Double(finalPrice);
	}
}
