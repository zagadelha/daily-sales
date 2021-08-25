package com.devskiller.orders;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OrdersAnalyzerTest {

	private List<OrdersAnalyzer.Order> orders;

	@Before
	public void setUp() throws Exception {
		orders = OrdersTestHelper.readOrders("/orders.json");
	}

	@Test
	public void shouldReturnCorrectValueForPencils() {
		// given
		OrdersAnalyzer ordersAnalyzer = new OrdersAnalyzer();

		// then
		OrdersTestHelper.testResults(ordersAnalyzer, 9872, orders);
	}

	@Test
	public void shouldReturnCorrectValueForPen() {
		// given
		OrdersAnalyzer ordersAnalyzer = new OrdersAnalyzer();

		// then
		OrdersTestHelper.testResults(ordersAnalyzer, 5723, orders);
	}

	@Test
	public void shouldReturnCorrectValueForEraserSet() {
		// given
		OrdersAnalyzer ordersAnalyzer = new OrdersAnalyzer();

		// then
		OrdersTestHelper.testResults(ordersAnalyzer, 3433, orders);
	}

	@Test
	public void shouldReturnCorrectValueForMarker() {
		// given
		OrdersAnalyzer ordersAnalyzer = new OrdersAnalyzer();

		// then
		OrdersTestHelper.testResults(ordersAnalyzer, 4098, orders);
	}

}
