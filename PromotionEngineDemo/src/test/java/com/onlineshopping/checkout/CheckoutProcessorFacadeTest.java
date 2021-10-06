package com.onlineshopping.checkout;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;

import com.onlineshopping.checkout.helper.PriceEvalExpHelper;
import com.onlineshopping.checkout.helper.PromotionHelper;
import com.onlineshopping.model.Promotion;
import com.onlineshopping.model.PromotionType;

public class CheckoutProcessorFacadeTest {
	
	public static final Double SCENARIO_A_PRICE = 100.0;
	public static final Double SCENARIO_B_PRICE = 370.0;
	public static final Double SCENARIO_C_PRICE = 280.0;
	
	CheckoutProcessorFacade checkoutProcessor;
	PriceEvalExpHelper priceEvalExpHelper;
	PromotionHelper promotionHelper;

	@Before
	public void setup() {
		checkoutProcessor = new CheckoutProcessorFacade();
		priceEvalExpHelper = new PriceEvalExpHelper();
		promotionHelper = new PromotionHelper();
		populateTestData();
	}
	
	@Test
	public void testCheckoutForScenarioA() throws ScriptException{
		Double price = checkoutProcessor.checkout("1*A|1*B|1*C");
		assertNotNull(price);
		assertTrue("Checkout Price "+price+ " is not matched", price - SCENARIO_A_PRICE == 0);
	}
	
	@Test
	public void testCheckoutForScenarioB() throws ScriptException{
		Double price = checkoutProcessor.checkout("5*A|5*B|1*C");
		assertNotNull(price);
		assertTrue("Checkout Price" + price + "is not matched", price - SCENARIO_B_PRICE == 0);
	}
	
	@Test
	public void testCheckoutForScenarioC() throws ScriptException{
		Double price = checkoutProcessor.checkout("3*A|5*B|1*C|1*D");
		assertNotNull(price);
		assertTrue("Checkout Price" + price + "is not matched", price - SCENARIO_C_PRICE == 0);
	}
	
	public void populateTestData() {
        promotionHelper.setPromotions(populatePromotions());
		priceEvalExpHelper.setPriceMap(populatePriceMap());
		checkoutProcessor.setPriceEvalExpHelper(priceEvalExpHelper);
		checkoutProcessor.setPromotionHelper(promotionHelper);
	}
	
	public List<Promotion> populatePromotions() {
		List<Promotion> promotions = new ArrayList<Promotion>();
		
		Promotion promotionOne = new Promotion();
		promotionOne.setActive(true);
		promotionOne.setDiscountCondition("A+A+A");
		promotionOne.setDiscount(new Double(130));
		promotionOne.setPromotionName("3 of A's for 130");
		promotionOne.setPromotionType(PromotionType.BUNCH_ITEM_DISCOUNT);
		
		Promotion promotionTwo = new Promotion();
		promotionTwo.setActive(true);
		promotionTwo.setDiscountCondition("B+B");
		promotionTwo.setDiscount(new Double(45));
		promotionTwo.setPromotionName("2 of B's for 45");
		promotionTwo.setPromotionType(PromotionType.BUNCH_ITEM_DISCOUNT);
		
		Promotion promotionThree = new Promotion();
		promotionThree.setActive(true);
		promotionThree.setDiscountCondition("C+D");
		promotionThree.setDiscount(new Double(30));
		promotionThree.setPromotionName("C & D for 30");
		promotionThree.setPromotionType(PromotionType.BUNCH_ITEM_DISCOUNT);
		
		promotions.add(promotionOne);
		promotions.add(promotionTwo);
		promotions.add(promotionThree);
		return promotions;
	}
	
	public Map<String,Double> populatePriceMap(){
		Map<String,Double> priceMap = new HashMap<>();
		priceMap.put("A", 50d);
		priceMap.put("B", 30d);
		priceMap.put("C", 20d);
		priceMap.put("D", 15d);
		return priceMap;
	}

}
