package com.airbornz.bbs.order;

import java.text.NumberFormat;
import java.util.Scanner;

import com.airbornz.bbs.Core;

public class CashRegister {

	/**
	 * Start method to print a reciept.
	 * Gets order number from the user.
	 * @param in Application Scanner instance.
	 */
	public static void printReciept(Scanner in) {
		System.out.println("Please enter the order number to print");
		int orderNum = in.nextInt();
		Order order = OrderManager.retrieveOrder(orderNum);
		if (order != null) { //If the order exists.
			printRecieptToScreen(order);
		}
		else {
			System.out.println("No order with that number exists, sending you back to the main menu!");
		}
	}
	
	/**
	 * Run by the printReceipt(Scanner) method to 
	 * print details to the screen.
	 * @param order The order to print.
	 */ 
	private static void printRecieptToScreen(Order order) {
		NumberFormat form = NumberFormat.getCurrencyInstance();
		Core.printHeader();
		System.out.println("============================");
		
		System.out.println("Order number: "+order.getOrderNum());
		System.out.println();
		for (MenuItem item : order.getItems()) {
			System.out.println(item.getDisplayName()+": "+form.format(item.getPrice()));
		}
		System.out.println();
		
		System.out.println("============================");
		
		
		double sub = getSubTotal(order);
		double tax = getTax(sub);
		double gratuity = getGratuity(tax+sub);
		double total = sub+tax+gratuity;
		
		System.out.println("Sub-total: "+form.format(sub));
		System.out.println("Tax (7%): "+form.format(tax));
		System.out.println("Gratuity (15%): "+form.format(gratuity));
		
		System.out.println("============================");
		
		System.out.println();
		System.out.println("Total: "+form.format(total));
	}
	
	/**
	 * Get the subtotal of the order.
	 * @param order The order.
	 * @return The subtotal of the order.
	 */
	public static double getSubTotal(Order order) {
		double total = 0;
		for (MenuItem item : order.getItems()) {
			total += item.getPrice();
		}
		return total;
	}
	
	/**
	 * Get the tax of the order.
	 * Tax is at 7%.
	 * @param subtotal The subtotal to run against.
	 * @return The tax of the subtotal.
	 */
	public static double getTax(double subtotal) {
		return subtotal * 0.07;
	}
	
	/**
	 * Get the gratuity amount of the check.
	 * Gratuity is at 15%.
	 * @param total The total of the order.
	 * @return The gratuity.
	 */
	public static double getGratuity(double total) {
		return total * 0.15;
	}
	
	/**
	 * Get payment for the order.
	 * @param in Appliaction Scanner instance.
	 */
	public static void getPayment(Scanner in) {
		System.out.println("How were we paying today?");
		System.out.println("(1) Cash");
		System.out.println("(2) Credit/Debit Card");
		int option = in.nextInt();
		while (option != 1 && option != 2) {
			System.out.println("Invalid option, please try again!");
		}
		System.out.println("Payment accepted!");
	}
	
}
