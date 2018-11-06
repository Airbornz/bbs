package com.airbornz.bbs.order;

import java.util.ArrayList;

public class OrderManager {

	protected static ArrayList<Order> orders = new ArrayList<Order>();
	protected static int nextNum = 0;
	
	/**
	 * Creates a unique order number for reference at cash register.
	 * @return int The unique order number.
	 */
	protected static int getOrderNumber() {
		nextNum++;
		return nextNum;
	}
	
	/**
	 * Get an order from an order number.
	 * @param orderNumber The order number to get back.
	 * @return The order or null if not found.
	 */
	public static Order retrieveOrder(int orderNumber) {
		for (Order order : orders) {
			if (order.getOrderNum() == orderNumber) {
				return order;
			}
		}
		return null;
	}
}
