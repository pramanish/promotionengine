package com.onlineshopping.promotion.impl;

import org.apache.commons.lang3.StringUtils;

import com.onlineshopping.promotion.api.DiscountProcessor;

public class BunchItemDiscountProcessor implements DiscountProcessor {

	@Override
	public String discount(String exp, String condition, String discount) {
		if(exp.contains(condition)) {
			exp = exp.replace(condition, discount);
			//exp = StringUtils.replaceAll(exp, condition, discount);
		}
		return exp;
	}

}
