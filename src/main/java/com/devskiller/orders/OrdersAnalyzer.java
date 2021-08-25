package com.devskiller.orders;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersAnalyzer {

	private Map<DayOfWeek, Integer> totalDailySales;
	private Integer total;

	static class Order {
		int orderId;
		LocalDateTime creationDate;
		List<OrderLine> orderLines;
	}

	static class OrderLine {
		int productId;
		String name;
		int quantity;
		BigDecimal unitPrice;
	}

	public Map<DayOfWeek, Integer> totalDailySales(int productId, List<Order> orders) {

		totalDailySales = new HashMap<>();
		total = 0;

		for (Order order : orders) {
			for (OrderLine line : order.orderLines) {

				if (line.productId != productId) {
					continue;
				}

				switch (order.creationDate.getDayOfWeek()) {
				case MONDAY:
					sumDailySales(DayOfWeek.MONDAY, line.quantity);
					break;

				case TUESDAY:
					sumDailySales(DayOfWeek.TUESDAY, line.quantity);
					break;

				case WEDNESDAY:
					sumDailySales(DayOfWeek.WEDNESDAY, line.quantity);
					break;

				case THURSDAY:
					sumDailySales(DayOfWeek.THURSDAY, line.quantity);
					break;

				case FRIDAY:
					sumDailySales(DayOfWeek.FRIDAY, line.quantity);
					break;

				case SATURDAY:
					sumDailySales(DayOfWeek.SATURDAY, line.quantity);
					break;

				case SUNDAY:
					sumDailySales(DayOfWeek.SUNDAY, line.quantity);
					break;
				}
			}
		}

		return totalDailySales;
	}

	private void sumDailySales(DayOfWeek day, int quantity) {

		total = totalDailySales.get(day) == null ? quantity : totalDailySales.get(day) + quantity;
		totalDailySales.put(day, total);
	}

}
