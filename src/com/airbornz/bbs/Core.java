package com.airbornz.bbs;

import java.util.Scanner;

import com.airbornz.bbs.order.CashRegister;
import com.airbornz.bbs.order.MenuItem;
import com.airbornz.bbs.order.Order;
import com.airbornz.bbs.order.OrderManager;

public class Core {

	public static void main(String[] args) throws InterruptedException {
		Scanner key = new Scanner(System.in);
		Core.printHeader();
		System.out.println("Starting up...");
		while (true) {
			Thread.sleep(1000); //Delay to improve readability
			printMainMenu(); //Print the main menu
			int option = key.nextInt();
			if (option == 6) //If shutdown
				break; //Get out of runtime loop which shutsdown.
			determineOption(option, key);
		}
		//Shutdown
		System.out.println("Bye!");
		key.close(); //Prevent memory leaks
	}
	
	/**
	 * Print the applications main menu.
	 */
	private static void printMainMenu() {
		System.out.println();
		System.out.println("============================");
		System.out.println("Welcome Server,");
		System.out.println("What would you like to do?");
		System.out.println("(1) Start An order");
		System.out.println("(2) Modify An order");
		System.out.println("(3) Print A Receipt");
		System.out.println("(4) Ring An Order");
		System.out.println("(5) Print Menu");
		System.out.println("(6) Shutdown");
		System.out.println("============================");
	}
	
	/**
	 * Determine which option was entered into the main menu and dispatch to next screen.
	 * @param option The option that was entered.
	 * @param in Application Scanner instance.
	 */
	private static void determineOption(int option, Scanner in) {
		switch (option) {
		case 1: startOrder(); return;
		case 2: modifyAnOrder(in); return;
		case 3: CashRegister.printReciept(in); return;
		case 4: CashRegister.getPayment(in); return;
		case 5: printMenu(); return;
		default: System.out.println("Invalid option please try again!"); return;
		}
	}
	
	/**
	 * Create a new order object and output its number to screen.
	 */
	private static void startOrder() {
		Order order = new Order();
		System.out.println("Success! Order number is "+order.getOrderNum());
	}
	
	/**
	 * Modify an order:
	 * Add items
	 * Remove items
	 * @param in Application Scanner instance
	 */
	private static void modifyAnOrder(Scanner in) {
		System.out.println("Please enter the order number to modify");
		int orderNum = in.nextInt();
		Order order = OrderManager.retrieveOrder(orderNum);
		boolean modify = true; //Keep modifying the order used in loop below.
		if (order != null) { //If the order exists
			while(modify) { //If user wants to keep modifying.
				System.out.println("Ok, were we adding or substracting an item (a/s)?");
				char option = in.next().charAt(0);
				if (option == 'a') { //Add item
					System.out.println("Enter the item number to add");
					MenuItem item = getItem(in);
					order.addItem(item);
				}
				else if (option == 's') { //Subtract item
					System.out.println("Enter the item number to subtract");
					MenuItem item = getItem(in);
					order.removeItem(item);			
				}
				else {
					System.out.println("That is an invalid option please try again!");
				}
				//Operation done, continue modifying?
				System.out.println("Done, did you want to keep modifing?");
				char mod = in.next().toLowerCase().charAt(0);
				modify = (mod == 'y');
			}		
		}
		else {
			System.out.println("No order with that number exists, sending you back to the main menu!");
		}
	}
	
	/**
	 * Get a MenuItem from the user.
	 * @param in Application Scanner instance.
	 * @return The user's entered MenuItem.
	 */
	private static MenuItem getItem(Scanner in) {
		int itemcode = in.nextInt();
		MenuItem item = MenuItem.fromNumber(itemcode);
		while (item == null) { //If item number is invalid
			System.out.println("Sorry, that is not a valid item number please enter a different number.");
			itemcode = in.nextInt();
			item = MenuItem.fromNumber(itemcode);
		}
		return item;
	}
	
	/**
	 * Print the restaurants menu.
	 */
	private static void printMenu() {
		int i = 0;
		while (i < 8) {
			i++;
			MenuItem item = MenuItem.fromNumber(i);
			System.out.println("("+i+") "+item.getDisplayName()+": "+item.getPrice());
		}
	}
	
	/**
	 * Print the restaurants logo.
	 */
	public static void printHeader() {
		System.out.println("  ____   _____ _____ \r\n" + 
				" |  _ \\ / ____/ ____|\r\n" + 
				" | |_) | (___| (___  \r\n" + 
				" |  _ < \\___ \\\\___ \\ \r\n" + 
				" | |_) |____) ____) |\r\n" + 
				" |____/|_____|_____/ \r\n" + 
				"                     \r\n" + 
				"                     ");
	}
	
	
}
