package com.airbornz.bbs.order;

import java.util.ArrayList;

public class Order {

	private int orderNum;
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	
	public Order() {
		orderNum = OrderManager.getOrderNumber();
		OrderManager.orders.add(this);
	}

	/**
	 * Get the order number.
	 * @return int The order number.
	 */
	public int getOrderNum() {
		return orderNum;
	}

	/**
	 * Get the items in the order.
	 * @return ArrayList<MenuItem> The items in the order.
	 */
	public ArrayList<MenuItem> getItems() {
		return items;
	}
	
	/**
	 * Add an item to the order.
	 * @param item The item to add.
	 */
	public void addItem(MenuItem item) {
		items.add(item);
	}
	
	/**
	 * Remove an item from the order.
	 * @param item The item to remove.
	 */
	public void removeItem(MenuItem item) {
		if (items.contains(item)) { //Prevent errors by making sure the item actaully is in the array.
			items.remove(item);
		}
	}
}
