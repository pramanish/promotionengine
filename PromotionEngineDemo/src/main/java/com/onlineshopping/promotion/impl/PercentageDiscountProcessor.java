package com.onlineshopping.promotion.impl;

import com.onlineshopping.promotion.api.DiscountProcessor;

public class PercentageDiscountProcessor implements DiscountProcessor {

	@Override
	public String discount(String exp, String condition, String discount) {
		if(exp.contains(condition)) {
			exp = exp.replace(condition, discount);
		}
		return exp;
	}

}
