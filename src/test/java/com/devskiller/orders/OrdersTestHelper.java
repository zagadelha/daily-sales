package com.devskiller.orders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.assertj.core.api.Condition;

import static org.assertj.core.api.Assertions.assertThat;

public class OrdersTestHelper {

	static class ZeroOrNull extends Condition<Integer> {

		@Override
		public boolean matches(Integer value) {
			return value == null || value.equals(0);
		}
	}

	static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
		@Override
		public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
			return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime();
		}
	}).create();
	static Type listType = new TypeToken<ArrayList<OrdersAnalyzer.Order>>() {
	}.getType();

	static List<OrdersAnalyzer.Order> readOrders(String fileName) throws URISyntaxException, FileNotFoundException {
		return gson.fromJson(new FileReader(Paths.get(OrdersAnalyzerTest.class.getResource(fileName).toURI()).toFile()), listType);
	}


	public static Map<DayOfWeek, Integer> expectedResults(int productId, List<OrdersAnalyzer.Order> orders) {
		Map<DayOfWeek, Integer> sums = new HashMap<>();

		for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
			sums.put(dayOfWeek, 0);
		}
		for (int idx = 0; idx < orders.size(); idx++) {
			OrdersAnalyzer.Order order = orders.get(idx);
			DayOfWeek dayOfWeek = order.creationDate.getDayOfWeek();
			for (int lineIdx = 0; lineIdx < order.orderLines.size(); lineIdx++) {
				OrdersAnalyzer.OrderLine orderLine = order.orderLines.get(lineIdx);
				if (orderLine.productId == productId) {
					sums.put(dayOfWeek, sums.get(dayOfWeek) + orderLine.quantity);
				}
			}
		}
		return sums;
	}

	private static void assertWeekdayValues(Integer productId, Map<DayOfWeek, Integer> result, Map<DayOfWeek, Integer> expected) {
		for (int i = 0; i < 7; i++) {
			DayOfWeek dayOfWeek = DayOfWeek.of(i + 1);
			Integer val = expected.get(dayOfWeek);
			if (val == null || val.equals(0)) {
				assertThat(result.get(dayOfWeek)).as("for productId=" + productId + " for " + dayOfWeek.name()).is(new ZeroOrNull());
			} else {
				assertThat(result.get(dayOfWeek)).as("for productId=" + productId + " for " + dayOfWeek.name()).isEqualTo(val);
			}
		}
	}

	static void testResults(OrdersAnalyzer ordersAnalyzer, Integer productId, List<OrdersAnalyzer.Order> orders) {
		Map<DayOfWeek, Integer> result = ordersAnalyzer.totalDailySales(productId, orders);
		Map<DayOfWeek, Integer> expectedResult = expectedResults(productId, orders);

		assertWeekdayValues(productId, result, expectedResult);
	}
}
