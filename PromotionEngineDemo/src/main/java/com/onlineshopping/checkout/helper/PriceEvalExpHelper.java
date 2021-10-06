package com.onlineshopping.checkout.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class PriceEvalExpHelper {
	
	Map<String,Double> priceMap = new HashMap<String,Double>();

	public Map<String, Double> getPriceMap() {
		return priceMap;
	}

	public void setPriceMap(Map<String, Double> priceMap) {
		this.priceMap = priceMap;
	}
	
	public OrderExp createOrderExpression(List<String> orders) {
		OrderExp orderExp = new OrderExp();
		orders.stream().forEach(orderValue -> {
			String orderValues  []= orderValue.split("\\*");
			int length = new Integer(orderValues[0]);			
			for(int i = 0; i< length; i++) { 
				orderExp.setExp(orderExp.getExp() + orderValues[1] +"+");
			}
		});
		if(orderExp.getExp().length() > 0){
			orderExp.setExp(orderExp.getExp().substring(0, orderExp.getExp().length()-1));
		}
		return orderExp;
	}
	
	public String addOriginalPriceInExp(String exp) {
		String [] expValues = exp.split("\\+");
		for(String expValue : expValues) {
			if(priceMap.containsKey(expValue)) {
				Double price = priceMap.get(expValue);
				exp = exp.replace(expValue, price+"");
			}
		}
		return exp;
	}
	
	public String evalPriceExp(OrderExp orderExp) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		Object result = engine.eval(orderExp.getExp());
		return result.toString();
	}
}
